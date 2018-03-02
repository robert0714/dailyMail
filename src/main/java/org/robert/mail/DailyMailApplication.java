package org.robert.mail;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
 

@SpringBootApplication
public class DailyMailApplication implements CommandLineRunner{
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

		}
		
	}
}
