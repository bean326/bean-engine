package com.bean.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils 
{

	public static String doAccessHTTPPost(String url)
	{
		return doAccessHTTPPost(url, "", "UTF-8");
	}
	
	public static String doAccessHTTPPost(String url,String param)
	{
		return doAccessHTTPPost(url, param, "UTF-8");
	}
	
	/**
	 * <p>
	 * POST方法
	 * </p>
	 * 
	 * @param sendUrl
	 *            ：访问URL
	 * @param sendParam
	 *            ：参数串
	 * @param backEncodType
	 *            ：返回的编码
	 * @return
	 */
	public static String doAccessHTTPPost(String sendUrl, String sendParam, String backEncodType)
	{

		StringBuffer receive = new StringBuffer();
		BufferedWriter wr = null;
		try {
			if (backEncodType == null || backEncodType.equals("")) {
				backEncodType = "UTF-8";
			}

			URL url = new URL(sendUrl);
			HttpURLConnection URLConn = (HttpURLConnection) url
					.openConnection();

			URLConn.setDoOutput(true);
			URLConn.setDoInput(true);
			((HttpURLConnection) URLConn).setRequestMethod("POST");
			URLConn.setUseCaches(false);
			URLConn.setAllowUserInteraction(true);
			HttpURLConnection.setFollowRedirects(true);
			URLConn.setInstanceFollowRedirects(true);

			URLConn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded;charset=UTF-8");
			URLConn.setRequestProperty("Content-Length", String
					.valueOf(sendParam.getBytes().length));

			DataOutputStream dos = new DataOutputStream(URLConn
					.getOutputStream());
			dos.writeBytes(sendParam);

			BufferedReader rd = new BufferedReader(new InputStreamReader(
					URLConn.getInputStream(), backEncodType));
			String line;
			while ((line = rd.readLine()) != null) {
				receive.append(line).append("\r\n");
			}
			rd.close();
		} catch (IOException e) {
			receive.append("访问产生了异常-->").append(e.getMessage());
			e.printStackTrace();
		} finally {
			if (wr != null) {
				try {
					wr.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				wr = null;
			}
		}

		return receive.toString();
	}
	
	public static String doAccessHTTPGet(String sendUrl, String backEncodType)
	{

		StringBuffer receive = new StringBuffer();
		BufferedReader in = null;
		try {
			if (backEncodType == null || backEncodType.equals("")) {
				backEncodType = "UTF-8";
			}

			URL url = new URL(sendUrl);
			HttpURLConnection URLConn = (HttpURLConnection) url
					.openConnection();

			URLConn.setDoInput(true);
			URLConn.setDoOutput(true);
			URLConn.connect();
			URLConn.getOutputStream().flush();
			in = new BufferedReader(new InputStreamReader(URLConn
					.getInputStream(), backEncodType));

			String line;
			while ((line = in.readLine()) != null) {
				receive.append(line).append("\r\n");
			}

		} catch (IOException e) {
			receive.append("访问产生了异常-->").append(e.getMessage());
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				in = null;

			}
		}

		return receive.toString();
	}
}
