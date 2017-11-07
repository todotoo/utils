package org.wg.mail.wait;

import java.io.File;

public class Email {

	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 收件人
	 */
	private String emailAddr;
	/**
	 * 邮件标题
	 */
	private String title;
	/**
	 * 邮件正文
	 */
	private String content;
	/**
	 * 附件
	 */
	private File attachment;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEmailAddr() {
		return emailAddr;
	}

	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public File getAttachment() {
		return attachment;
	}

	public void setAttachment(File attachment) {
		this.attachment = attachment;
	}

}
