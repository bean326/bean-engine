package com.bean.util;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;


/**
 * @author Bean 
 */
public class Utils {
	

	/**
	 * 格式yyyyMMdd1111
	 * @return
	 */
	public static String getBatchNo()
	{
		
		return TimeUtils.getFormatTime(System.currentTimeMillis(), "yyyyMMdd") + new Random().nextInt(10000);
	}
    
	
	/**
	 * 生成支付交易号  eg:20150914100250-9545
	 * @return
	 */
	public static synchronized String generatePayBatchNo(){
		String batchNo = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String datetime = sdf.format(new Date());
		int hashCode = UUID.randomUUID().toString().hashCode();
		if(hashCode < 0){
			hashCode = hashCode * -1;
		}
		String hashCodeStr = String.valueOf(hashCode);
		if(hashCodeStr.length() > 16){
			hashCodeStr = hashCodeStr.substring(0,16);
		}
		batchNo = datetime + "-" + hashCodeStr;
//		int randomNo = new Random().nextInt(10000);
//		batchNo = datetime + "-" + randomNo;
		return batchNo;
	}
	
	
    public static String getLocalhostip() 
    {
        try {
            InetAddress thisIp = InetAddress.getLocalHost();
            return thisIp.getHostAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String longtoip(long longip) 
    {
        StringBuffer sb = new StringBuffer("");
        sb.append(String.valueOf(longip >>> 24));// 直接右移24位
        sb.append(".");
        sb.append(String.valueOf((longip & 0x00ffffff) >>> 16)); // 将高8位置0，然后右移16位
        sb.append(".");
        sb.append(String.valueOf((longip & 0x0000ffff) >>> 8));
        sb.append(".");
        sb.append(String.valueOf(longip & 0x000000ff));
        return sb.toString();
    }

    public static long iptolong(String strip) 
    {
 
        long[] ip = new long[4];
        int position1 = strip.indexOf(".");
        int position2 = strip.indexOf(".", position1 + 1);
        int position3 = strip.indexOf(".", position2 + 1);
        ip[0] = Long.parseLong(strip.substring(0, position1));
        ip[1] = Long.parseLong(strip.substring(position1 + 1, position2));
        ip[2] = Long.parseLong(strip.substring(position2 + 1, position3));
        ip[3] = Long.parseLong(strip.substring(position3 + 1));
        return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3]; // ip1*256*256*256+ip2*256*256+ip3*256+ip4
    }

   


    /**
     * 计算地球上任意两点(经纬度)距离
     *
     * @param long1 第一点经度
     * @param lat1  第一点纬度
     * @param long2 第二点经度
     * @param lat2  第二点纬度
     * @return 返回距离 单位：米
     */
    public static double countDistance(double long1, double lat1, double long2,
                                       double lat2) {
        double a, b, R;
        R = 6378137; // 地球半径
        lat1 = lat1 * Math.PI / 180.0;
        lat2 = lat2 * Math.PI / 180.0;
        a = lat1 - lat2;
        b = (long1 - long2) * Math.PI / 180.0;
        double d;
        double sa2, sb2;
        sa2 = Math.sin(a / 2.0);
        sb2 = Math.sin(b / 2.0);
        d = 2
                * R
                * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1)
                * Math.cos(lat2) * sb2 * sb2));
        return d;
    }

    

    /**
     * 获取星座： 1： "摩羯座"；2： "水平座"；3： "双鱼座" 4： "白羊座"；5： "金牛座"；6： "双子座" 7： "巨蟹座"；8：
     * "狮子座"；9： "处女座" 10："天秤座"；11："天蝎座"；12："射手座"
     *
     * @param month
     * @param day
     * @return
     */
    public static int getConstellation(int month, int day) 
    {
        if (month == 1) {
            if (day <= 20) {
                return 1;
            } else {
                return 2;
            }
        }

        if (month == 2) {
            if (day <= 18) {
                return 2;
            } else {
                return 3;
            }
        }

        if (month == 3) {
            if (day <= 20) {
                return 3;
            } else {
                return 4;
            }
        }

        if (month == 4) {
            if (day <= 19) {
                return 4;
            } else {
                return 5;
            }
        }

        if (month == 5) {
            if (day <= 20) {
                return 5;
            } else {
                return 6;
            }
        }

        if (month == 6) {
            if (day <= 21) {
                return 6;
            } else {
                return 7;
            }
        }

        if (month == 7) {
            if (day <= 22) {
                return 7;
            } else {
                return 8;
            }
        }

        if (month == 8) {
            if (day <= 22) {
                return 8;
            } else {
                return 9;
            }
        }

        if (month == 9) {
            if (day <= 22) {
                return 9;
            } else {
                return 10;
            }
        }

        if (month == 10) {
            if (day <= 23) {
                return 10;
            } else {
                return 11;
            }
        }

        if (month == 11) {
            if (day <= 22) {
                return 11;
            } else {
                return 12;
            }
        }

        if (month == 12) {
            if (day <= 21) {
                return 12;
            } else {
                return 1;
            }
        }

        return 0;

    }
	
	/** 得到两个数之间的随即数 */
	public static int getRandomInt(int max,int min)
	{
		int length = 0;
		if(max == min)
			length = 2;
		else
			length = (max - min) + 1;
		int[] tempInts = new int[length];
		for (int i = 0; i < tempInts.length; i++)
		{
			if(max == min)
				tempInts[i] = min;
			else
				tempInts[i] = min++;
		}
		return tempInts[(int)(length * Math.random())];
	}
	

	 /**  
	    * 将int数值转换为占四个字节的byte数组，本方法适用于(低位在前，高位在后)的顺序。  
	    * @param value  
	    *            要转换的int值 
	    * @return byte数组 
	    */    
	public static byte[] intToBytes(int value)   
	{   
	    byte[] byte_src = new byte[4];  
	    byte_src[3] = (byte) ((value & 0xFF000000)>>24);  
	    byte_src[2] = (byte) ((value & 0x00FF0000)>>16);  
	    byte_src[1] = (byte) ((value & 0x0000FF00)>>8);    
	    byte_src[0] = (byte) ((value & 0x000000FF));          
	    return byte_src;  
	}  
	
	/**  
	    * byte数组中取int数值，本方法适用于(低位在前，高位在后)的顺序。 
	    *   
	    * @param ary  
	    *            byte数组  
	    * @return int数值  
	    */    
	public static int bytesToInt(byte[] ary)
	{  
	    int value;    
	    int offset = 0;
	    value = (int) ((ary[offset]&0xFF)   
	            | ((ary[offset+1]<<8) & 0xFF00)  
	            | ((ary[offset+2]<<16)& 0xFF0000)   
	            | ((ary[offset+3]<<24) & 0xFF000000));  
	    return value;  
	}  
	
	/**
	 * 把字符串包装成List
	 * @param str
	 * @param splitType
	 * @return
	 */
	public static List<String> getListByStr(String str,String splitType)
	{
		if(Utils.isNull(str))
			return null;
		String[] strs = str.split(splitType);
		List<String> list = new ArrayList<String>();
		for(String s  : strs)
		{
			list.add(s);
		}
		return list;
	}

	/**
	 * 把集合里的数据组合成字符串,默认用逗号隔开
	 * @param <T>
	 * @param list
	 * @return
	 */
	public static <T> String getStrByList(Collection<T> list)
	{
		if(list == null || list.size() == 0)
			return "";

		StringBuilder result = new StringBuilder();
		for(Object o : list)
		{
			result.append(o);
			result.append(",");
		}
		return result.substring(0,result.length()-1);
	}
    
	public static boolean isNull(Object obj)
	{
		if(obj == null)
			return true;
		
		if(obj instanceof String)
		{
			String str = obj.toString().replaceAll(" ", "");
			if(str.isEmpty() || str.length() == 0 || str.equals(""))
				return true;
		}
		return false;
	}

	public static String generateUUID() 
	{
        return UUID.randomUUID().toString().replace("-", "");
    }
	/**
	 * 生成随机号 英文 和数字随机顺序
	 * @param num
	 * @return
	 */
	public static String generateUUID(int num, boolean add)
	{
		String[] strs = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s",
				"t","u","v","w","x","y","z","0","1","2","3","4","5","6","7","8","9",".","_","+","-","*","/"};
		String time = System.currentTimeMillis()+"";
		StringBuffer sb = new StringBuffer();
		int r = 0;
		for (int i = 0; i < num; i++)
		{
			r = (int) (Math.random()*strs.length);
			sb.append(strs[r]);
		}
		sb.append(time.substring(time.length()-3));
		if(add)
		{
			sb.append("-");
			sb.append((int)Math.random()*10000);
		}
		return sb.toString();
	}
	
	/**
	 * 生成支付交易号  eg:20150914100250-9545
	 * @return
	 */
	public static synchronized String generateTradeNo()
	{
		String batchNo = "";
		SimpleDateFormat sdf = new SimpleDateFormat(TimeUtils.YYYYMMDDHHMMSS);
		String datetime = sdf.format(new Date());
		int hashCode = UUID.randomUUID().toString().hashCode();
		if(hashCode < 0){
			hashCode = hashCode * -1;
		}
		String hashCodeStr = String.valueOf(hashCode);
		if(hashCodeStr.length() > 16){
			hashCodeStr = hashCodeStr.substring(0,16);
		}
		batchNo = datetime + "-" + hashCodeStr;
		return batchNo;
	}
	
	
	
	public static String generatePromoCode() 
	{
		return generateRandomNum(8);
	}

	public static synchronized String generateRandomNum(int len) 
	{
		// 35是因为数组是从0开始的，26个字母+10个数字
		final int maxNum = 36;
		int i; // 生成的随机数
		int count = 0; // 生成的长度
		char[] firstStr = { '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		char[] str = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		boolean isFirst = true;
		while (count < len) {
			// 生成随机数，取绝对值，防止生成负数，
			i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1
			if(isFirst && i >= 0 && i < firstStr.length){
				isFirst = false;
				pwd.append(firstStr[i]);
				count++;
			}else if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}

		return pwd.toString();
	}
	

	
}
