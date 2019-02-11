package xyz.dymasun.ws.rule;

import xyz.dymasun.ws.utils.Constant;

import java.io.IOException;
import java.util.Properties;
import java.util.regex.Pattern;

public class RuleUtils {
	public static boolean checkID(String id){
		return Pattern.matches(Constant.REG_ID_15, id) || Pattern.matches(Constant.REG_ID_18, id);
	}
	public static boolean isLinux;
	static{
		Properties prop = System.getProperties();
		String os = prop.getProperty("os.name");
		if (os != null && os.toLowerCase().indexOf("linux") > -1) {
			isLinux = true;
		} else {
			isLinux = false;
		}
	}
	public static void exec(String cmd){
		String[] cmds = new String[]{null,null,cmd};
		if(isLinux){
			cmds[0]="/bin/sh";
			cmds[1]="-c";
		}else{
			cmds[0]="cmd";
			cmds[1]="/c";
		}
		try {
			Runtime.getRuntime().exec(cmds);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
