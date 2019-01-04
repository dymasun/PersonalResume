package xyz.dymasun.resume.service;

import xyz.dymasun.resume.rule.RuleResult;

public interface IMailService {
	RuleResult sendToMe(String title,String msg);
	RuleResult sendToOne(String dst,String title,String msg);
}
