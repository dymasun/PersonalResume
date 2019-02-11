package xyz.dymasun.ws.service;

import com.alibaba.fastjson.JSONObject;

public interface IThirdpartyInfoService {
	JSONObject getInfoOfIpAddress(String ip);
}
