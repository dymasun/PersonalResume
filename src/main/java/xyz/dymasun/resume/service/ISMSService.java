package xyz.dymasun.resume.service;

import xyz.dymasun.resume.rule.RuleResult;

public interface ISMSService {
	RuleResult sendMsg(String dstPhone,String msg);
	RuleResult sendMsg(String msg);
}
