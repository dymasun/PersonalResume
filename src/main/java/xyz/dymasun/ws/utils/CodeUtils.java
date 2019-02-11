package xyz.dymasun.ws.utils;

import java.util.Random;
import java.util.UUID;

//import org.apache.shiro.crypto.hash.Md5Hash;

public class CodeUtils {
	//通过异或将文字加密
	public static String encodeStr(String str){
		if(str==null||"".equals(str))
			return null;
		StringBuffer result = new StringBuffer();
		for(int i=0;i<str.length();i++){
			result.append((char)(str.charAt(i)^str.length()));
		}
		return result.toString();
	}
	public static String generateUUID(){
		String uuid = UUID.randomUUID().toString()
				.replaceAll("-","");
		return uuid;
	}
	public static String getRandomString2(int length){
	    //产生随机数
	    Random random=new Random();
	    StringBuffer sb=new StringBuffer();
	    //循环length次
	    for(int i=0; i<length; i++){
	      //产生0-2个随机数，既与a-z，A-Z，0-9三种可能
	      int number=random.nextInt(3);
	      long result=0;
	      switch(number){
	      //如果number产生的是数字0；
	      case 0:
	        //产生A-Z的ASCII码
	        result=Math.round(Math.random()*25+65);
	        //将ASCII码转换成字符
	        sb.append(String.valueOf((char)result));
	        break;
	        case 1:
	          //产生a-z的ASCII码
	        result=Math.round(Math.random()*25+97);
	          sb.append(String.valueOf((char)result));
	        break;
	        case 2:
	          //产生0-9的数字
				sb.append(String.valueOf
	                          (new Random().nextInt(10)));
	        break; 
	      }
	    }
	    return sb.toString();
	  }
}
