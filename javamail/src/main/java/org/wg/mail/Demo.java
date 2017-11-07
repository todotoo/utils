package org.wg.mail;

import org.junit.Test;

import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Properties;

public class Demo {
	@Test
	public void fun1() throws AddressException, MessagingException {
		String host = "smtp.163.com";
		Properties prop = new Properties();
		// 发件人的邮箱的 SMTP 服务器地址
		prop.setProperty("mail.host", host);
		// 需要请求认证
		prop.setProperty("mail.smtp.auth", "true");

		Authenticator auth = new Authenticator() {
			@Override
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("bianyi_run", "gang0000");
			}
		};

		Session session = Session.getDefaultInstance(prop, auth);

		session.setDebug(true);

		MimeMessage msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress("bianyi_run@163.com"));
		// 收件人
		msg.addRecipients(RecipientType.TO, "bianyi.run@aliyun.com, bianyi.run@foxmail.com");
		// 抄送人
//		msg.addRecipients(RecipientType.CC, "******");
		// 秘密抄送
//		msg.addRecipients(RecipientType.BCC, "******");
		msg.setSubject("需要遥看", "utf-8");
		msg.setText("创建一个部件", "utf-8");

		Transport.send(msg);
	}

	@Test
	public void fun2() throws AddressException, MessagingException, IOException {
		Properties prop = new Properties();
		prop.setProperty("mail.host", "smtp.163.com");
		prop.setProperty("mail.smtp.auth", "true");

		Authenticator auth = new Authenticator() {
			@Override
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("itcast_cxf", "itcast");
			}
		};

		Session session = Session.getDefaultInstance(prop, auth);

		session.setDebug(false);

		MimeMessage msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress("itcast_cxf@163.com"));
		msg.addRecipients(RecipientType.TO, "itcast_cxf@qq.com");
		msg.setSubject("这是一封带有附件的邮件！");

		MimeMultipart parts = new MimeMultipart();

		MimeBodyPart part1 = new MimeBodyPart();
		part1.setContent("<h1><font color='red'>这是一封带有附件的邮件，用来做测试的，请不要在意！</font></h1>", "text/html;charset=utf-8");
		parts.addBodyPart(part1);

//		MimeBodyPart part1 = new MimeBodyPart();
//		part1.attachFile("F:\\maze-game.swf");
//		part1.setFileName("maze-game.swf");
//		parts.addBodyPart(part1);

		MimeBodyPart part2 = new MimeBodyPart();
		part2.attachFile("F:\\白冰.jpg");
		part2.setFileName(MimeUtility.encodeText("白冰.jpg"));
		parts.addBodyPart(part2);

//		MimeBodyPart part3 = new MimeBodyPart();
//		part3.attachFile("F:\\没离开过.mp3");
//		part3.setFileName("没离开过.mp3");
//		parts.addBodyPart(part3);

		msg.setContent(parts);

		Transport.send(msg);
	}

	@Test
	public void fun3() throws MessagingException, IOException {
		Mail mail = new Mail("bianyi_run@163.com", "1289868863@qq.com");
		mail.setSubject("多哈哈哈");
		mail.setContent("这是一封测试邮件,不要太在意!");

		Session session = MailUtils.createSession("smtp.163.com", "bianyi_run", "gang0000");
		MailUtils.send(session, mail);
	}
}
