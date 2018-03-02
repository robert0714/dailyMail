package develop.service.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractMailProvider {
	private Logger logger = LoggerFactory.getLogger(MailProvider.class);

	private final String username;
	private final String password;
	private final String host;
	private final Integer port;
	private final String fromer;
	private final boolean starttls;
	private final boolean auth;

	abstract void setContent (final MimeMessage message)throws MessagingException;

	public AbstractMailProvider(String username, String password, String host, Integer port, String fromer,
			boolean starttls, boolean auth) {
		super();
		this.username = username;
		this.password = password;
		this.host = host;
		this.port = port;
		this.fromer = fromer;
		this.starttls = starttls;
		this.auth = auth;
	}
	
	public void send(final String to, final String subject ) {
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", String.valueOf(auth));
		props.put("mail.smtp.starttls.enable", String.valueOf(starttls));
		props.put("mail.smtp.port", port);
		
		// 根據中華電信機房同仁表示java Mail實作會retry多次，smtp server防止垃圾信機制會啟動
		props.put("mail.smtp.connectiontimeout", 3000);
		props.put("mail.smtp.timeout", 3000);
		
		 
		
		Session session;
		if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
			session = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});
		} else {
			// 中華電信的smtp server(無帳號密碼)發送多人信件，要用以下方法維持session
			session = Session.getInstance(props);
		}
		Transport transport = null;
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromer));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);

			setContent(message);

			transport = session.getTransport("smtp");

			if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
//				transport.connect(host, port, username, password);
			} else {
				// 中華電信的smtp server(無帳號密碼)發送多人信件時，會阻擋發信
				/**
				 * (Reference: https://docs.oracle.com/javaee/7/api/javax/mail/Transport.html )
				 * public static void send(Message msg) throws MessagingException
				 * 
				 * Note that send is a static method that creates and manages its own
				 * connection. Any connection associated with any Transport instance used to
				 * invoke this method is ignored and not used. This method should only be
				 * invoked using the form Transport.send(msg);, and should never be invoked
				 * using an instance variable.
				 * 
				 * Transport是繼承Service這個Class，而Transport本身沒有connect這個method。
				 * 所以如果要用Transport.send(Message)這個method，就不要額外再呼叫 transport.connect()
				 * (原因上面doc有寫)。 這也是為什麼每次他發信都會有一些"「did not issue MAIL/EXPN/VRFY/ETRN during
				 * connection to MTA」訊息"。 更可怕的是它沒有tranport.close()，這樣可能會keep一些連線沒有release而造成"
				 * 「Too many SMTP connection ....」"
				 */
//				transport.connect();
			}
			/***
			 * 中華電信review後反應，Transport是繼承Service這個Class，而Transport本身沒有connect這個method。<br/>
			 * 所以如果要用Transport.send(Message)這個method，就不要額外再呼叫 transport.connect()
			 * (原因上面doc有寫)。<br/>
			 * 這也是為什麼每次他發信都會有一些"「did not issue MAIL/EXPN/VRFY/ETRN during connection to
			 * MTA」訊息"。 *
			 **/
			Transport.send(message);

			logger.info("寄送email結束.");
			logger.info("to: {}", to);
		} catch (MessagingException e) {
			logger.error("寄送email失敗.");
			logger.error("mail.smtp.host: {}", host);
			logger.error("mail.smtp.auth: {}", auth);
			logger.error("mail.smtp.starttls.enable: {}", starttls);
			logger.error("mail.smtp.port: {}", port);
			logger.error("mail.smtp.port: {}", port);
			logger.error("to: {}", to);
			logger.error("fromer: {}", fromer);

			throw new RuntimeException(e);
		} finally {
			/***
			 * 中華電信review後反應，它沒有tranport.close()，這樣可能會keep一些連線沒有release而造成" 「Too many SMTP
			 * connection ....」"			 * 
			 **/
			if (transport != null) {
				try {
					transport.close();
				} catch (MessagingException e) {
					logger.error("transport.close 失敗.");
					logger.error(e.getMessage(), e);
				}
			}
		}
	}

	
	
	
}
