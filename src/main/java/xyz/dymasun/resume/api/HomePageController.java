package xyz.dymasun.resume.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.dymasun.resume.rule.RuleResult;
import xyz.dymasun.resume.service.IMailService;
import xyz.dymasun.resume.service.ISMSService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/home")
public class HomePageController {

	@Autowired
	private ISMSService smsService;

	@Autowired
	private IMailService mailService;
	@RequestMapping(value="/msg",
			produces = "application/json;charset=utf-8",
			method = RequestMethod.POST
	)
	@ResponseBody
	public String sendMsg(
			@RequestParam(value = "name",required = false) String name,
			@RequestParam(value = "email",required = false) String email,
			@RequestParam(value = "phone",required = false) String phone,
			@RequestParam(value = "msg",required = false) String msg){
		System.out.println("name:"+name
				+"\nemail:"+email
				+"\nphone:"+phone
				+"\nmsg:"+msg);
		String message = name +"["+phone+"]["+email+"]:"+msg;
		mailService.sendToMe("[联系我]",message);
		return RuleResult.success().toJSON();
	}
	@RequestMapping(value="/test",
			produces = "application/json;charset=utf-8",
			method = RequestMethod.GET
	)
	@ResponseBody
	public String test(){
		smsService.sendMsg("fasfa");
		return RuleResult.success().toJSON();
	}
	@RequestMapping(value = "/guest",
			produces = "application/json;charset=utf-8",
			method = RequestMethod.GET
	)
	@ResponseBody
	public String guest(HttpServletRequest request){
		System.out.println("有访客来访"+"IP地址为:"+request.getRemoteHost()+"\t" + request.getRemoteAddr());
//        return RuleResult.success().toJSON();
		return mailService.sendToMe("有访客来访","IP地址为:"+request.getRemoteHost()+"\t" + request.getRemoteAddr()).toJSON();
	}
}
