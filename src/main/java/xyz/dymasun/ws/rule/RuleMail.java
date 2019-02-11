package xyz.dymasun.ws.rule;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class RuleMail {
	public void sendMail(String dstMail,String sub,String content)throws MessagingException {
		if (!ts.isConnected()){
			init();
		}
		//创建邮件对象
		MimeMessage message = new MimeMessage(session);
		//指明邮件的发件人
		message.setFrom(new InternetAddress(user));
		//指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(dstMail));
		//邮件的标题
		message.setSubject(sub);
		//邮件的文本内容
		message.setContent(content, "text/html;charset=UTF-8");
		//5、发送邮件
		ts.sendMessage(message, message.getAllRecipients());
	}
	private String mailHost;
	private String mailProtocol;
	private boolean auth;
	private String user;
	private String pwd;
	private Transport ts;

	public String getMailHost() {
		return mailHost;
	}

	public void setMailHost(String mailHost) {
		this.mailHost = mailHost;
	}

	public String getMailProtocol() {
		return mailProtocol;
	}

	public void setMailProtocol(String mailProtocol) {
		this.mailProtocol = mailProtocol;
	}

	public boolean isAuth() {
		return auth;
	}

	public void setAuth(boolean auth) {
		this.auth = auth;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	private Session session;
	public RuleMail() {
	}
	public void init(){
		Properties prop = new Properties();
		prop.setProperty("mail.host", mailHost);
		prop.setProperty("mail.transport.protocol", mailProtocol);
		prop.setProperty("mail.smtp.auth", String.valueOf(auth));
		session = Session.getInstance(prop);
		try {
			ts = session.getTransport();
			ts.connect(mailHost, user, pwd);
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void destory(){
		try {
			ts.close();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
