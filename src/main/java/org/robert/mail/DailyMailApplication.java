package org.robert.mail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
 

@SpringBootApplication
public class DailyMailApplication implements CommandLineRunner{
	/** The log. */
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	/** The receiver. */
	@Value("${develop.service.maas.SpgatewayTradeStatusService.receiver:robert.lee@iisigroup.com,hz.tseng@iisigroup.com}")
	private String receiver;
	
	/** The mail provider. */
	@Autowired
	private MailProvider mailProvider;
	
	
	@Value("${org.robert.mail.attachment:${HOME}}")
	private String folder ;

	public static void main(String[] args)  {
		SpringApplication.run(DailyMailApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		File origin =new File(folder);
		if(!origin.exists()) {
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
				 
				BodyPart messageBodyPart = new MimeBodyPart();
			 
				String filename = origin.getName();

				FileInputStream fis = new FileInputStream(output);
				ByteArrayDataSource rawData = new ByteArrayDataSource(fis, "application/octet-stream");
				DataHandler data = new DataHandler(rawData);

				messageBodyPart.setDataHandler(data);
				messageBodyPart.setFileName(filename);
				mailProvider.send(receiver, origin.getName(), messageBodyPart);

			} catch (IOException | MessagingException e) {
				log.error(e.getMessage(), e);
			}  
		}
		
	}
}
