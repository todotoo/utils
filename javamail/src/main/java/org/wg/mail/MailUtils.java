package org.wg.mail;

import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * 邮件工具类
 * @author wg
 */
public class MailUtils {

	/**
	 * 发送指定的邮件
	 *
	 * @param mail
	 */
	public static void send(Session session, Mail mail) throws MessagingException, IOException {
		MimeMessage message = createMimeMessage2(session, mail);
		// 发邮件
		Transport.send(message);
	}


	/**
	 * 根据配置创建会话对象, 用于和邮件服务器交互
	 *
	 * @param host     发件人邮箱的 SMTP 服务器地址, 必须准确, 不同邮件服务器地址不同, 一般(只是一般, 绝非绝对)格式为: smtp.xxx.com
	 *                 网易163邮箱的 SMTP 服务器地址为: smtp.163.com
	 * @param username 用户名
	 * @param password 密码
	 *                 某些邮箱服务器为了增加邮箱本身密码的安全性，给 SMTP 客户端设置了独立密码（有的邮箱称为“授权码”）,
	 *                 对于开启了独立密码的邮箱, 这里的邮箱密码必需使用这个独立密码（授权码）。
	 * @return
	 */
	public static Session createSession(String host, final String username, final String password) {
		Properties prop = new Properties();
		prop.setProperty("mail.host", host);
		// 需要请求认证
		prop.setProperty("mail.smtp.auth", "true");

		// 创建验证器
		Authenticator auth = new Authenticator() {
			@Override
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		};

		// 获取session对象
		Session session = Session.getInstance(prop, auth);
		// 设置为debug模式, 可以查看详细的发送 log
		session.setDebug(true);
		return session;
	}

	/**
	 * 创建一封只包含文本的简单邮件
	 *
	 * @param session     和服务器交互的会话
	 * @param mail        邮件对象
	 * @return
	 * @throws Exception
	 */
	public static MimeMessage createMimeMessage(Session session, Mail mail) throws Exception {
		// 1. 创建一封邮件
		MimeMessage message = new MimeMessage(session);

		// 2. From: 发件人
		message.setFrom(new InternetAddress(mail.getFrom()));

		// 3. To: 收件人
		message.addRecipients(RecipientType.TO, mail.getToAddress());
		// 设置抄送
		String cc = mail.getCcAddress();
		if (!cc.isEmpty()) {
			message.addRecipients(RecipientType.CC, cc);
		}
		// 设置暗送
		String bcc = mail.getBccAddress();
		if (!bcc.isEmpty()) {
			message.addRecipients(RecipientType.BCC, bcc);
		}
		// 4. Subject: 邮件主题
		message.setSubject(mail.getSubject(), "UTF-8");

		// 5. Content: 邮件正文（可以使用html标签）
		message.setContent(mail.getContent(), "text/html;charset=UTF-8");

		// 6. 设置发件时间
		message.setSentDate(new Date());

		// 7. 保存设置
		message.saveChanges();

		return message;
	}

	/**
	 * 创建一封只包含文本的简单邮件
	 * @param session 和服务器交互的会话
	 * @param mail    邮件对象
	 * @return
	 * @throws Exception
	 */
	public static MimeMessage createMimeMessage2(Session session, Mail mail) throws MessagingException, IOException {
		// 创建邮件对象
		MimeMessage msg = new MimeMessage(session);
		// 设置发件人
		msg.setFrom(new InternetAddress(mail.getFrom()));
		// 设置收件人
		msg.addRecipients(RecipientType.TO, mail.getToAddress());
		// 设置抄送
		String cc = mail.getCcAddress();
		if (!cc.isEmpty()) {
			msg.addRecipients(RecipientType.CC, cc);
		}
		// 设置暗送
		String bcc = mail.getBccAddress();
		if (!bcc.isEmpty()) {
			msg.addRecipients(RecipientType.BCC, bcc);
		}
		// 设置主题
		msg.setSubject(mail.getSubject());
		// 创建部件集对象
		MimeMultipart parts = new MimeMultipart();
		// 创建一个部件
		MimeBodyPart part = new MimeBodyPart();
		// 设置邮件文本内容
		part.setContent(mail.getContent(), "text/html;charset=utf-8");
		// 把部件添加到部件集中
		parts.addBodyPart(part);
		// 添加附件
		List<AttachBean> attachBeanList = mail.getAttachs();
		if (attachBeanList != null) {
			for (AttachBean attach : attachBeanList) {
				// 创建一个部件
				MimeBodyPart attachPart = new MimeBodyPart();
				// 设置附件文件
				attachPart.attachFile(attach.getFile());
				// 设置附件文件名
				attachPart.setFileName(MimeUtility.encodeText(attach.getFileName()));
				parts.addBodyPart(attachPart);
			}
		}
		// 给邮件设置内容
		msg.setContent(parts);
		return msg;
	}
}
