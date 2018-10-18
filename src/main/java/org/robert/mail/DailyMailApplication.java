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

import develop.service.mail.MailProvider;
import develop.service.sms.SMSService;

@SpringBootApplication(scanBasePackages = { "develop.service" })
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
	
	@Value("${org.robert.sms.content:test}")
	private String content;
	
	/** The mail provider. */
	@Autowired
	private MailProvider mailProvider;

	@Value("${org.robert.mail.attachment:${HOME}}")
	private String folder;
	@Value("${org.robert.mail.titlePrefix: }")
	private String titlePrefix;
	@Value("${org.robert.mail.titleSufffix: }")
	private String titleSufffix;
	
	@Autowired
	@Qualifier("hinet")
	private SMSService aSMSService ;

	public static void main(String[] args) {
		SpringApplication.run(DailyMailApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {		
		File origin = new File(folder);
		
		if ((!BooleanUtils.toBoolean(isSMS)) && (!origin.exists())) {
			System.out.println(folder + " does not exist ! ");

			return;
		}
		
		if (origin.exists() && origin.isDirectory()) {
			// 如果是資料夾進行壓縮再process

		} else if (origin.exists() && origin.isFile()){
			File output = origin;
			try {
				System.out.println(output.getAbsolutePath());
				System.out.println("titlePrefix: " + titlePrefix);
				System.out.println("titleSufffix: " + titleSufffix);
				String title = titlePrefix + origin.getName() + titleSufffix;
				
				
				
				BodyPart messageBodyPart = new MimeBodyPart();
				String filename = origin.getName();
				//email附件處理
				FileInputStream fis = new FileInputStream(output);
				ByteArrayDataSource rawData = new ByteArrayDataSource(fis, "application/octet-stream");
				DataHandler data = new DataHandler(rawData);

				messageBodyPart.setDataHandler(data);
				messageBodyPart.setFileName(filename);
				//發動email
				mailProvider.send(receiver, title, messageBodyPart);
				

			} catch (Exception  e) {
				log.error(e.getMessage(), e);
			}
		}
		if(BooleanUtils.toBoolean(isSMS)) {
			final	String[] phones = StringUtils.split(receiverPhoneNumbers,",");
			for(String phone : phones ) {				
				aSMSService.send(content, StringUtils.trim(phone));
			}
			
		} 
	}
}
