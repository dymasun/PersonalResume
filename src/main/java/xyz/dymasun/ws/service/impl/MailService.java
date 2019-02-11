package xyz.dymasun.ws.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import xyz.dymasun.ws.rule.*;
import xyz.dymasun.ws.service.IMailService;

import javax.mail.MessagingException;

@Service
public class MailService implements IMailService {
	@Autowired
	private RuleMail ruleMail;
	@Autowired
	private RulePool rulePool;
	@Value("${mailUser}")
	public String myMail;
	public RuleResult sendToMe(String title, String msg) {
		rulePool.add(new RuleWorker() {
			@Override
			public void doMethod(RuleMap param) {
				try {
					ruleMail.sendMail(myMail,(String)param.get("title"),(String)param.get("msg"));
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}
		}.params(RuleMap.create().put("title",title).put("msg",msg)));
		return RuleResult.success();
	}

	public RuleResult sendToOne(String dst, String title, String msg) {
		rulePool.add(new RuleWorker() {
			@Override
			public void doMethod(RuleMap param) {
				try {
					ruleMail.sendMail((String)param.get("dst"),(String)param.get("title"),(String)param.get("msg"));
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}
		}.params(RuleMap.create().put("title",title).put("msg",msg).put("dst",dst)));
		return RuleResult.success();
	}
}
