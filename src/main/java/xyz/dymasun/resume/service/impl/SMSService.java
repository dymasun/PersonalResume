package xyz.dymasun.resume.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import xyz.dymasun.resume.http.HttpService;
import xyz.dymasun.resume.rule.RuleMap;
import xyz.dymasun.resume.rule.RulePool;
import xyz.dymasun.resume.rule.RuleResult;
import xyz.dymasun.resume.rule.RuleWorker;
import xyz.dymasun.resume.service.ISMSService;
@Service("smsService")
public class SMSService implements ISMSService {

	@Value("${myPhone}")
	private String myPhone;
	@Value("${SMSUid}")
	private String SMSUid;
	@Value("${SMSKey}")
	private String SMSKey;
	@Value("${SMSAPI}")
	private String SMSAPI;
	@Autowired
	private RulePool rulePool;
	@Autowired
	private HttpService httpService;
	public RuleResult sendMsg(String dstPhone, String msg) {
		rulePool.add(new RuleWorker() {
			@Override
			public void doMethod(RuleMap param) {

				try {
					String response = httpService.post(SMSAPI,
							RuleMap.create()
									.put("Uid",SMSUid)
									.put("Key",SMSKey)
									.put(param),
							"application/x-www-form-urlencoded;charset=utf-8");
					if (null == response)throw new Exception();
					int code = Integer.parseInt(response);
					if (code == 0) System.out.println("发送失败");
					switch (code){
						//-1	没有该用户账户
						case -1:
							System.out.println("没有该用户账户");
							break;
						//-2	接口密钥不正确 [查看密钥]
						//不是账户登陆密码
						case -2:
							System.out.println("接口密钥不正确");
							break;
						//-21	MD5接口密钥加密不正确
						case -21:
							System.out.println("MD5接口密钥加密不正确");
							break;
						//-3	短信数量不足
						case -3:
							System.out.println("短信数量不足");
							break;
						//-11	该用户被禁用
						case -11:
							System.out.println("该用户被禁用");
							break;
						//-14	短信内容出现非法字符
						case -14:
							System.out.println("短信内容出现非法字符");
							break;
						//-4	手机号格式不正确
						case -4:
							System.out.println("手机号格式不正确");
							break;
						//-41	手机号码为空
						case -41:
							System.out.println("手机号码为空");
							break;
						//-42	短信内容为空
						case -42:
							System.out.println("短信签名太长");
							break;
						//-51	短信签名格式不正确
						//接口签名格式为：【签名内容】
						case -51:
							System.out.println("短信签名太长");
							break;
						//-52	短信签名太长
						//建议签名10个字符以内
						case -52:
							System.out.println("短信签名太长");
							break;
						//-6	IP限制
						case -6:
							System.out.println("IP限制");
							break;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.params(RuleMap.create()
				.put("smsMob",dstPhone)
				.put("smsText",msg)));
		return RuleResult.success();
	}

	public RuleResult sendMsg(String msg) {
		return sendMsg(myPhone,msg);
	}
}
