package cn.tedu.note.util;

import org.apache.commons.codec.digest.DigestUtils;

public class Utils {
	
	public static final String salt = "好的";

	public static String crypt(String pwd){
		return DigestUtils.md5Hex(pwd+salt);
	}
}
