package xyz.dymasun.ws.service;

import xyz.dymasun.ws.rule.RuleResult;

public interface ISMSService {
	RuleResult sendMsg(String dstPhone, String msg);
	RuleResult sendMsg(String msg);
}
