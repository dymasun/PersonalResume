package xyz.dymasun.resume.rule;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author sun
 *	返回值规定格式
 */
public class RuleResult extends BaseRule {
	//返回值
	private int code;
	//返回消息
	private String msg;
	//返回数据
	private Object data;
	private RuleResult(){
		
	}
	//不带任何数据的成功代码是204
	public static RuleResult success(){
		RuleResult result = new RuleResult();
		result.setCode(204);
		return result;
	}
	//带数据的成功代码是200
	public static RuleResult success(Object data){
		RuleResult result = new RuleResult();
		result.setCode(200);
		result.setData(data);
		return result;
	}
	//失败了返回错误信息,错误码500
	public static RuleResult fail(String msg){
		RuleResult result = new RuleResult();
		result.setCode(500);
		result.setMsg(msg);
		return result;
	}
	public static RuleResult generate(int code,String msg,Object data){
		RuleResult result = new RuleResult();
		return result;
	}
	@Override
	public String toJSON() {
		return JSONObject.toJSONString(this);
	}

	public int getCode() {
		return code;
	}

	public RuleResult setCode(int code) {
		this.code = code;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public RuleResult setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
