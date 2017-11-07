package org.wg.mail.wait;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;


public class EmailHandler {

	/**
	 * @param email
	 * @throws AddressException
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 * @Title: send
	 * @Description: 邮件发送
	 */
	public static void send(Email email) throws AddressException, MessagingException,
			UnsupportedEncodingException {
		if (email != null) {
			final PropertiesUtils propertiesUtils = new PropertiesUtils("message.properties");
			//构造认证信息
			Properties props = new Properties();
			String mailHost = propertiesUtils.getProperty("MESSAGE.MAIL_SMTP_HOST");
			final String userName = propertiesUtils.getProperty("MESSAGE.MAIL_USER_NAME");
			final String password = propertiesUtils.getProperty("MESSAGE.MAIL_USER_PASSWORD");
			String personalname = propertiesUtils.getProperty("MESSAGE.MAIL_USER_PERSONALNAME");
			props.put("mail.smtp.host", mailHost);
			props.put("mail.smtp.auth", "true");
			props.setProperty("mail.smtp.user", userName);
			props.setProperty("mail.smtp.password", password);
			props.setProperty("mail.smtp.auth", "true");
			Session session = Session.getDefaultInstance(props,
					new Authenticator() {
						@Override
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(userName, password);
						}
					});

			//构造邮件对象
			MimeMessage message = new MimeMessage(session);
			message.setSubject(email.getTitle());
			message.setSentDate(new Date());
			Address address = new InternetAddress(userName, personalname);
			message.setFrom(address);
			Multipart multipart = new MimeMultipart();
			BodyPart html = new MimeBodyPart();
			html.setContent(email.getContent(), "text/html; charset=utf-8");
			multipart.addBodyPart(html);
			if (email.getAttachment() != null) {
				BodyPart attachmentBodyPart = new MimeBodyPart();
				DataSource source = new FileDataSource(email.getAttachment());
				attachmentBodyPart.setDataHandler(new DataHandler(source));
				attachmentBodyPart.setFileName(MimeUtility.encodeWord(email.getAttachment().getName()));
				multipart.addBodyPart(attachmentBodyPart);
			}
			message.setContent(multipart);
			message.addRecipients(Message.RecipientType.TO, new Address[]{new InternetAddress(email.getEmailAddr())});
			Transport.send(message);
		}
	}
}
