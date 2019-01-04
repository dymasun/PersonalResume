package xyz.dymasun.resume.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;


public class ParamSignUtil {

	public static void main(String[] args) throws IOException
	{
		HashMap<String, Object> signMap = new HashMap<String, Object>();
		signMap.put("a","01");
		signMap.put("c","02");
		signMap.put("b","03");
		String secret="test";	//商定的密钥
		System.out.println("SignHashMap:"+getSignature(signMap,secret));//bd626368b251b23a71cd5d96c428b958
		//最终的请求参数
		//http://www.xxx.com/api/user?a=01&c=02&b=03&sign=bd626368b251b23a71cd5d96c428b958
	}

	
	/**
	 * 签名生成算法
	 * @param Map<String,Object> params 请求参数集，所有参数必须已转换为字符串类型
	 * @param String secret 签名密钥
	 * @return 签名
	 * @throws IOException
	 */
	public static String getSignature(Map<String,Object> params, String secret) throws IOException
	{
		// 先将参数以其参数名的字典序升序进行排序
		Map<String, Object> sortedParams = new TreeMap<String, Object>(params);
		Set<Entry<String, Object>> entrys = sortedParams.entrySet();
	 
		// 遍历排序后的字典，将所有参数按"key=value"格式拼接在一起
		StringBuilder basestring = new StringBuilder();
		for (Entry<String, Object> param : entrys) {
			basestring.append(param.getKey()).append("=").append(param.getValue());
		}
		basestring.append(secret);
	 
		// 使用MD5对待签名串求签
		byte[] bytes = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			bytes = md5.digest(basestring.toString().getBytes("UTF-8"));
		} catch (GeneralSecurityException ex) {
		}
	 
		// 将MD5输出的二进制结果转换为小写的十六进制
		StringBuilder sign = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(bytes[i] & 0xFF);
			if (hex.length() == 1) {
				sign.append("0");
			}
			sign.append(hex);
		}
		return sign.toString();
	}
	
	/**
	 * 签名生成算法
	 * @param JSON对象 请求参数集，所有参数必须已转换为字符串类型
	 * @param String secret 签名密钥
	 * @return 签名
	 * @throws IOException
	 */
	public static String getSignature(JSONObject jo, String secret) throws IOException
	{
		Map<String, Object> map = convertJsonStrToMap(jo.toJSONString());
		//jo.put("sign", getSignature(map, secret));
		return getSignature(map, secret);
	}
	
	/**
	 * 签名生成算法
	 * @param JSON字符串 请求参数集，所有参数必须已转换为字符串类型
	 * @param String secret 签名密钥
	 * @return 签名
	 * @throws IOException
	 */
	public static String getSignature(String jo, String secret) throws IOException
	{
		Map<String, Object> map = convertJsonStrToMap(jo);
		//JSONObject newjo = JSON.parseObject(jo);
		//newjo.put("sign", getSignature(map, secret));
		return getSignature(map, secret);
	}
	
	/**
     * 将json转化成map
     * @param jsonStr
     * @return
     */
    public static Map<String, Object>  convertJsonStrToMap(String jsonStr){
        
        Map<String, Object> map = JSON.parseObject(
                jsonStr,new TypeReference<Map<String, Object>>(){} );
        
        return map;
    }
}
