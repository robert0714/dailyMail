package develop.service.mail;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
 

@Component
public class MailProvider {

	private Logger logger = LoggerFactory.getLogger(MailProvider.class);
	
	@Value("${develop.service.mail.smtp.username:iisigroup.bear@gmail.com}")
	private String username;
	
	@Value("${develop.service.mail.smtp.password:iYoYo*(^(}")
	private String password;
	
	
	@Value("${develop.service.mail.smtp.host:smtp.gmail.com}")
	private String host;
	
	
	@Value("${develop.service.mail.smtp.port:587}")
	private Integer port;
	
	@Value("${develop.service.mail.smtp.fromer:iisigroup.bear@gmail.com}")
	private String fromer;
	
	@Value("${develop.service.mail.smtp.starttls:true}")
	private boolean starttls;
	
	@Value("${develop.service.mail.smtp.auth:true}")
	private boolean auth;
	
	
	public void send(String to, String subject, String text)   {
		send(to , subject , text , "plain");
	}
	
	public void sendHtml(String to, String subject, String htmlBody)   {
		String text = "";
		if (htmlBody != null && !htmlBody.toUpperCase().startsWith("<HTML>") ) {
			text = htmlBody;
		} else {
			text = "<html><head></head><body>" + htmlBody + "</body></html>";
		}
		send(to , subject , text , "html");
	}
	
	
	public void send(String to, String subject, String text , String type) {		
		AbstractMailProvider attatchMailProvider = new AbstractMailProvider(username, password, host, port, fromer,
				starttls, auth) {
			@Override
			void setContent(MimeMessage message) throws MessagingException {
				message.setText(text , java.nio.charset.StandardCharsets.UTF_8.toString(), type);
			}
		};

		attatchMailProvider.send(to, subject);
	}
	 
	public void send(final  String to, final  String subject,final  BodyPart... messageBodyParts ) {
		AbstractMailProvider attatchMailProvider = new AbstractMailProvider(username, password, host, port, fromer,
				starttls, auth) {
			@Override
			void setContent(MimeMessage message) throws MessagingException {
				Multipart multipart = new MimeMultipart();
				for(BodyPart messageBodyPart: messageBodyParts) {
					multipart.addBodyPart(messageBodyPart);
				}				 
				message.setContent(multipart);
			}
		};

		attatchMailProvider.send(to, subject);
	}
}
