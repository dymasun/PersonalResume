package xyz.dymasun.ws.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.dymasun.ws.http.HttpService;
import xyz.dymasun.ws.service.IThirdpartyInfoService;

//
//                   _ooOoo_
//                  o8888888o
//                  88" . "88
//                  (| -_- |)
//                  O\  =  /O
//               ____/'---'\____
//             .'  \\|     |//  '.
//            /  \\|||  :  |||//  \
//           /  _||||| -:- |||||-  \
//           |   | \\\  -  /// |   |
//           | \_|  ''\---/''  |   |
//           \  .-\__  `-`  ___/-. /
//         ___'. .'  /--.--\  '. . __
//      ."" '<  '.___\_<|>_/___.'  >'"".
//     | | :  '- \'.;'\ _ /';.'/ - ' : | |
//     \  \ '-.   \_ __\ /__ _/   .-' /  /
//======'-.____`-.___\_____/___.-`____.-'======
//                   `=---='
//       佛祖保佑                   永无BUG
//       本模块已经经过开光处理，绝无可能再产生BUG
@Service
public class ThirdpartyInfoService implements IThirdpartyInfoService {
	@Autowired
	private HttpService httpService;
	@Override
	public JSONObject getInfoOfIpAddress(String ip) {
		if (null == ip)
			return null;
		String url = "http://ip-api.com/json/" + ip;
		String response = null;
		try {
			response = httpService.get(url);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (null == response)
			return null;
		return JSONObject.parseObject(response);
	}
}
