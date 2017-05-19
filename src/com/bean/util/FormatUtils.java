package com.bean.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author FXW
 * 2016年1月14日
 */
public class FormatUtils {

	/** 手机号格式规范 */
	static String PHONE_FORMAT = "^(13[0-9]|15[012356789]|17[0678]|18[0-9]|14[57])[0-9]{8}$";
	/** 生日格式规范 */
	static String BRITHDAY_FORMAT = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
	/** 创建角色界名称格式规范 */
	static String NAME_FORMAT = "^[A-Za-z0-9\u4e00-\u9fa5]{0,}$";
	/**
	 * 验证手机号格式
	 * @param name
	 * @return
	 */
	public static boolean checkPhoneFormat(String phone) {
		Pattern pattern = Pattern.compile(PHONE_FORMAT);
		Matcher matcher = pattern.matcher(phone);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}
	
	/**
	 * 验证生日格式
	 * @param name
	 * @return
	 */
	public static boolean checkBrithdayFormat(String birthday) {
		Pattern pattern = Pattern.compile(BRITHDAY_FORMAT);
		Matcher matcher = pattern.matcher(birthday);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}
	
	/**
	 * 验证昵称字符格式
	 * @param nickName
	 * @return
	 */
	public static boolean checkNickNameFormat(String nickName){
		Pattern pattern = Pattern.compile(NAME_FORMAT);
		Matcher matcher = pattern.matcher(nickName);
		if (matcher.matches()) {
			return true;
		}
		return false;
		
	}
}
