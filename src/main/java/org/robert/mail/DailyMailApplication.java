package org.robert.mail;

import java.io.File;
import java.io.FileInputStream;

import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.util.ByteArrayDataSource;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Import;

import develop.service.conf.MailConfig;
import develop.service.conf.SMSConfig;
import develop.service.mail.MailProvider;
import develop.service.sms.SMSService;
import develop.service.sftp.SftpMain;

@SpringBootApplication(scanBasePackages = { "develop.service" }, exclude = {
		EmbeddedServletContainerAutoConfiguration.class, WebMvcAutoConfiguration.class })
@Import({ MailConfig.class, SMSConfig.class })
public class DailyMailApplication implements CommandLineRunner {
	/** The log. */
	private Logger log = LoggerFactory.getLogger(this.getClass());

	/** The receiver. */
	@Value("${org.robert.mail:robert.lee@iisigroup.com,lincdot@gmail.com}")
	private String receiver;

	/** The receiver. */
	@Value("${org.robert.phoneNumbers:0920301309,0919952517}")
	private String receiverPhoneNumbers;

	@Value("${org.robert.isSMS:false}")
	private String isSMS;

	@Value("${org.robert.isSFTPDetect:false}")
	private String isSFTPDetect;

	@Value("${org.robert.sms.content:test}")
	private String content;

	/** The mail provider. */
	@Autowired
	private MailProvider mailProvider;

	@Value("${org.robert.mail.attachment:}")
	private String folder;
	@Value("${org.robert.mail.titlePrefix: }")
	private String titlePrefix;
	@Value("${org.robert.mail.titleSufffix: }")
	private String titleSufffix;

	@Autowired
	@Qualifier("hinet")
	private SMSService aSMSService;

	@Autowired
	private SftpMain sftpMainService;

	@Value("${org.robert.sftpUrl:202.39.225.89}")
	private String sftpUrl;

	public static void main(String[] args) {
		SpringApplication.run(DailyMailApplication.class, args);
	}

	public String getFolderPath() {
		if (StringUtils.isEmpty(folder)) {
			folder = System.getProperty("user.dir");

		}
		return folder;
	}
	@Override
	public void run(String... arg0) throws Exception {
		File origin = new File(getFolderPath());

		if ((!BooleanUtils.toBoolean(isSMS)) && (!origin.exists())) {
			System.out.println(getFolderPath() + " does not exist ! ");

			return;
		}

		if (origin.exists() && origin.isDirectory()) {
			// 如果是資料夾進行壓縮再process

		} else if (origin.exists() && origin.isFile()) {
			File output = origin;
			try {
				System.out.println(output.getAbsolutePath());
				System.out.println("titlePrefix: " + titlePrefix);
				System.out.println("titleSufffix: " + titleSufffix);
				String title = titlePrefix + origin.getName() + titleSufffix;

				BodyPart messageBodyPart = new MimeBodyPart();
				String filename = origin.getName();
				// email附件處理
				FileInputStream fis = new FileInputStream(output);
				ByteArrayDataSource rawData = new ByteArrayDataSource(fis, "application/octet-stream");
				DataHandler data = new DataHandler(rawData);

				messageBodyPart.setDataHandler(data);
				messageBodyPart.setFileName(filename);
				// 發動email
				mailProvider.send(receiver, title, messageBodyPart);

			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
		}
		if (BooleanUtils.toBoolean(isSMS)) {
			final String[] phones = StringUtils.split(receiverPhoneNumbers, ",");
			for (String phone : phones) {
				aSMSService.send(content, StringUtils.trim(phone));
			}

		} else if (BooleanUtils.toBoolean(isSFTPDetect)) {
			try {
				String screen = sftpMainService.ls(sftpUrl);
				log.info(screen);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}

		}
	}
}
