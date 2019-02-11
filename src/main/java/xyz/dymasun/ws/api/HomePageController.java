package xyz.dymasun.ws.api;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.dymasun.ws.rule.RuleResult;
import xyz.dymasun.ws.service.IMailService;
import xyz.dymasun.ws.service.ISMSService;
import xyz.dymasun.ws.service.IThirdpartyInfoService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

@Controller
@RequestMapping("/home")
public class HomePageController {

	@Autowired
	private ISMSService smsService;
	@Autowired
	private IThirdpartyInfoService thirdpartyInfoService;
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
	@RequestMapping(value = "/guest",
			produces = "application/json;charset=utf-8",
			method = RequestMethod.GET
	)
	@ResponseBody
	public String guest(HttpServletRequest request){
		JSONObject json = thirdpartyInfoService.getInfoOfIpAddress(request.getRemoteHost());
		String msg = null;
		if (null == json || !json.getString("status").equals("success")){
			msg = "IP地址为:"+request.getRemoteHost()+"\n" + request.getRemoteAddr();
		}else{
			msg = "有访客来访"
				+"\nas:"+json.getString("as")
				+"\ncity:"+json.getString("city")
				+"\nregionName:"+json.getString("regionName")
				+"\ncountry:"+json.getString("country")
				+"\nip:"+json.getString("query");
		}
		smsService.sendMsg(msg.replaceAll("\n","||"));
		mailService.sendToMe("有访客来访",msg);
        return RuleResult.success().toJSON();
	}
}
