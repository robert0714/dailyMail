package org.robert.mail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.util.ByteArrayDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import develop.service.mail.MailProvider;

@SpringBootApplication(scanBasePackages = { "develop.service.mail" })
public class DailyMailApplication implements CommandLineRunner {
	/** The log. */
	private Logger log = LoggerFactory.getLogger(this.getClass());

	/** The receiver. */
	@Value("${org.robert.mail:robert.lee@iisigroup.com,lincdot@gmail.com}")
	private String receiver;

	/** The mail provider. */
	@Autowired
	private MailProvider mailProvider;

	@Value("${org.robert.mail.attachment:${HOME}}")
	private String folder;
	@Value("${org.robert.mail.titlePrefix: }")
	private String titlePrefix;
	@Value("${org.robert.mail.titleSufffix: }")
	private String titleSufffix;

	public static void main(String[] args) {
		SpringApplication.run(DailyMailApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		File origin = new File(folder);
		if (!origin.exists()) {
			System.out.println(folder + " does not exist ! ");
			;
			return;
		}
		if (origin.isDirectory()) {
			// 如果是資料夾進行壓縮再process

		} else {
			File output = origin;
			try {
				System.out.println(output.getAbsolutePath());
				System.out.println("titlePrefix: " + titlePrefix);
				System.out.println("titleSufffix: " + titleSufffix);
				BodyPart messageBodyPart = new MimeBodyPart();

				String filename = origin.getName();

				String title = titlePrfix + origin.getName() + titleSufffix;

				FileInputStream fis = new FileInputStream(output);
				ByteArrayDataSource rawData = new ByteArrayDataSource(fis, "application/octet-stream");
				DataHandler data = new DataHandler(rawData);

				messageBodyPart.setDataHandler(data);
				messageBodyPart.setFileName(filename);
				mailProvider.send(receiver, title, messageBodyPart);

			} catch (IOException | MessagingException e) {
				log.error(e.getMessage(), e);
			}
		}

	}
}
