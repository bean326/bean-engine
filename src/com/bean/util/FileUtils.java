package com.bean.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import com.bean.code.ConstantCode;
import com.bean.object.EntityObject;

public class FileUtils 
{

	

	
	/**
	 * 读取文件
	 * @param fileName
	 * @return
	 */
	public static String readFile(String fileName)
	{
		StringBuffer sb = new StringBuffer();
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader reader = null;
		try
		{
			fis = new FileInputStream(new File(fileName));
			isr = new InputStreamReader(fis,"utf-8");
			reader = new BufferedReader(isr);
			
			String line = "";
			while((line=reader.readLine()) != null)
			{
				sb.append(line);
				sb.append(System.lineSeparator());
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				if(reader != null) reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(isr != null) isr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(fis != null) fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	/**
	 * 写文件-如果文件存在的话会覆盖原来文件内容
	 * @param fileName
	 * @param data
	 */
	public static void writeFile(String fileName,String data)
	{
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		BufferedWriter writer = null;
		try
		{
			fos = new FileOutputStream(new File(fileName));
			osw = new OutputStreamWriter(fos,"UTF-8");
			writer = new BufferedWriter(osw);
			
			writer.write(data);
			writer.flush();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				if(writer != null) writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(osw != null) osw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(fos != null) fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 写文件-如果文件存在，则在原来的内容上添加
	 * @param fileName
	 * @param data
	 */
	public static void writeFileAppend(String fileName,String data)
	{
		File file = new File(fileName);
		StringBuilder content = new StringBuilder();
		if(file.exists())
		{
			content.append(readFile(fileName));
		}
		content.append(data);
		writeFile(fileName, content.toString());
	}

	
	public static <T> ArrayList<T> loadFileVariables(InputStream inputStream, Class<T> tClass) throws IOException, InstantiationException, IllegalAccessException
	{
		ArrayList<T> list = new ArrayList<T>();
		
		if(!(tClass.newInstance() instanceof EntityObject))
		{
			System.out.println("LoadFileVariables Error:"+tClass);
			return list;
		}
		if(inputStream == null)
		{
			System.out.println("LoadFileVariables Error:"+inputStream);
			return list;
		}
		InputStreamReader isr = new InputStreamReader(inputStream, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		String line = null;
		br.readLine();//第一行是中文注释,不需要
		String[] property = br.readLine().split(ConstantCode.REGEX);
		
		while ((line = br.readLine()) != null) 
		{
			if (line == null || line.equals(""))
				continue;
			String[] value = line.split(ConstantCode.REGEX);
			T info = tClass.newInstance();
			for (int i = 0; i < property.length; i++)
			{
				if(info instanceof EntityObject)
					((EntityObject) info).setVariable(property[i].trim(), value[i].trim());
			}
			list.add(info);
		}
		isr.close();
		br.close();
		return list;
	}
	
	
	public static <T> ArrayList<T> loadFileVariables(File file, Class<T> tClass) throws IOException, InstantiationException, IllegalAccessException
	{
		ArrayList<T> list = new ArrayList<T>();
		if(!file.exists())
		{
			System.out.println("LoadFileVariables Error:"+tClass);
			return list;
		}
		
		if(!(tClass.newInstance() instanceof EntityObject))
		{
			System.out.println("LoadFileVariables Error:"+tClass);
			return list;
		}
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		String line = null;
		br.readLine();//第一行是中文注释,不需要
		String[] property = br.readLine().split(ConstantCode.REGEX);
		
		while ((line = br.readLine()) != null) 
		{
			if (line == null || line.equals(""))
				continue;
			String[] value = line.split(ConstantCode.REGEX);
			T info = tClass.newInstance();
			for (int i = 0; i < property.length; i++)
			{
				if(info instanceof EntityObject)
					((EntityObject) info).setVariable(property[i].trim(), value[i].trim());
			}
			list.add(info);
		}
		fis.close();
		isr.close();
		br.close();
		return list;
	}
	

	public static <T> ArrayList<T> loadFileVariables(String path, Class<T> tClass) throws IOException, InstantiationException, IllegalAccessException
	{
		ArrayList<T> list = new ArrayList<T>();
		
		if(!(tClass.newInstance() instanceof EntityObject))
		{
			System.out.println("LoadFileVariables Error:"+tClass);
			return list;
		}
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		String line = null;
		br.readLine();//第一行是中文注释,不需要
		String[] property = br.readLine().split(ConstantCode.REGEX);
		
		while ((line = br.readLine()) != null) 
		{
			if (line == null || line.equals(""))
				continue;
			String[] value = line.split(ConstantCode.REGEX);
			T info = tClass.newInstance();
			for (int i = 0; i < property.length; i++)
			{
				if(info instanceof EntityObject)
					((EntityObject) info).setVariable(property[i].trim(), value[i].trim());
			}
			list.add(info);
		}
		fis.close();
		isr.close();
		br.close();
		return list;
	}
	
	/**
	 * 直接根据具体的内容 加载 
	 * @param datas
	 * @param tClass
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static <T> ArrayList<T> loadFileVariablesByData(String datas,Class<T> tClass) throws InstantiationException, IllegalAccessException
	{
		ArrayList<T> list = new ArrayList<T>();
		
		if(datas == null || datas.length() == 0)
			return list;
		
		if(!(tClass.newInstance() instanceof EntityObject))
		{
			System.out.println("LoadFileVariables Error:"+tClass);
			return list;
		}
		
		String[] strs = datas.split(System.lineSeparator());
		String[] property = strs[1].split(ConstantCode.REGEX);
		for (int i = 2; i < strs.length; i++)
		{
			if(strs[i].length() == 0)
				continue;
			String[] value = strs[i].split(ConstantCode.REGEX);
			T info = tClass.newInstance();
			for (int j = 0; j < property.length; j++)
			{
				if(info instanceof EntityObject)
					((EntityObject) info).setVariable(property[j].trim(), value[j].trim());
			}
			list.add(info);
		}
		return list;
	}
	
	/**
	 * 根据文件路径把文件一行一行的读取
	 * @param path
	 * @return
	 * @throws java.io.IOException
	 */
	public static ArrayList<String> loadFile(String path) throws IOException
	{
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader reader = null;
		ArrayList<String> list = new ArrayList<String>();
		try
		{
			fis = new FileInputStream(new File(path));
			isr = new InputStreamReader(fis,"utf-8");
			reader = new BufferedReader(isr);
			
			String line = "";
			while((line=reader.readLine()) != null)
			{
				if(line.equals(""))
					continue;
				list.add(line);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				if(reader != null) reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(isr != null) isr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(fis != null) fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
	
	
	
}
