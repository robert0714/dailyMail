package develop.service.sftp;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Vector;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session; 

@Component
public class SftpMain {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${develop.service.sftp.passwd:os!QAZ2wsx3edc}")
	private String passwd;

	@Value("${develop.service.sftp.username:pntmaas}")
	private String user;

	private int port = 22;

	public String ls(final String host) {
		String result = null;
		JSch jsch = new JSch();
		Session session = null;
		String p2 = ".";
		try {
			session = createSession(jsch, host);

			session.connect();

			ChannelSftp c = createChannelSftp(session);

			c.connect();

			Vector<LsEntry> vector = c.ls(p2);
			StringBuilder sb =new StringBuilder();
			for (int i = 0; i < vector.size(); ++i) {
				LsEntry unit = vector.get(i);
				
				sb.append(unit.getFilename()).append(System.getProperty("line.separator"));
			}
			result = sb.toString();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			session.disconnect();
		}
		return result;
	}
	public String downloadAsString(final String host,String targetFileName) {
		String result = null;
		JSch jsch = new JSch();
		Session session = null;
		InputStream is=null;
		try {
			session = createSession(jsch, host);

			session.connect();

			ChannelSftp c = createChannelSftp(session);

			c.connect();

			is=c.get(targetFileName );
			result = IOUtils.toString(is, StandardCharsets.UTF_8);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			session.disconnect();
		}
		return result;
	}
	public boolean upload(String host, InputStream is,String targetFileName) {
		boolean result = false;
		JSch jsch = new JSch();
		Session session = null;
		String p2 = "."+File.separator+targetFileName;
		try {
			session = createSession(jsch, host);
			 
			session.connect();
			 
			ChannelSftp c = createChannelSftp(session);
			 
			c.connect();
			
			c.put(is, p2, ChannelSftp.OVERWRITE);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			session.disconnect();
		}
		return result;
	}
	public boolean upload(String host, File file) {
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream(file);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return upload(host, fis,file.getName());
		
	}
	

	public boolean upload(String host, String filePath ) {
		boolean result = false;
		JSch jsch = new JSch();
		Session session = null;
		String p2 = ".";
		try {
			session = createSession(jsch, host);
			 
			session.connect();
			 
			ChannelSftp c = createChannelSftp(session);
			 
			c.connect();
			
			c.put(filePath, p2,   ChannelSftp.OVERWRITE);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			session.disconnect();
		}
		return result;
	}
	protected Session createSession(JSch jsch, String host) throws JSchException {
		Session session = jsch.getSession(user, host, port);
		session.setPassword(passwd);

		// extra config code
		java.util.Properties config = new java.util.Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		return session;
	}
	protected ChannelSftp createChannelSftp(Session session) throws JSchException  {
		Channel channel = session.openChannel("sftp");
		ChannelSftp c = (ChannelSftp) channel;		 
		return c;
	}
}