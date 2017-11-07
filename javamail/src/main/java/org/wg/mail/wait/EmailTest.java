package org.wg.mail.wait;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by win7 on 2017/11/6.
 */
public class EmailTest {

	public static void main(String[] args) {
		List<Email> emails = new ArrayList<Email>();
		Email email = new Email();
		email.setTitle("哈哈哈");
		email.setContent("跌跌撞撞骂过土加速一");
//		email.setEmailAddr("1289868863@qq.com");
//		email.setEmailAddr("gongnijiwai@outlook.com");
		email.setEmailAddr("bianyi_run@163.com");
		emails.add(email);
		sendEmail(emails);
	}

	private static void sendEmail(List<Email> emails) {
		if (null != emails && emails.size() > 0) {
			for (Email email : emails) {
				try {
					EmailHandler.send(email);
				} catch (AddressException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
