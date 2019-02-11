package xyz.dymasun.ws.rule;

import com.alibaba.fastjson.JSONObject;

/**
 * 所有规则的父类
 */
public class BaseRule {
	//将对象转化为json字符串
	public String toJSON(){
		return JSONObject.toJSONString(this);
	}

	@Override
	public String toString() {
		return JSONObject.toJSONStringWithDateFormat(this,"yyyy-MM-dd HH:mm:ss");
	}
}
