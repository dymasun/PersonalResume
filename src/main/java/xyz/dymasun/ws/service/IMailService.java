package xyz.dymasun.ws.service;

import xyz.dymasun.ws.rule.RuleResult;

public interface IMailService {
	RuleResult sendToMe(String title, String msg);
	RuleResult sendToOne(String dst, String title, String msg);
}
