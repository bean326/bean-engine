package com.bean.util;

import java.util.ArrayList;
import java.util.List;

public class ArrayUtils {

	public static <T> T[] addObject(T[] objs, T obj)
	{
		List<T> list = new ArrayList<T>();
		for(T o : objs)
		{
			list.add(o);
		}
		list.add(obj);
		return list.toArray(objs);
	}
	
	public static <T> boolean isExist(T[] objs, T obj)
	{
		for(T o : objs)
		{
			if(o == obj)
				return true;
		}
		return false;
	}
	
	public static boolean isIntExist(int[] objs, int obj)
	{
		for(int o : objs)
		{
			if(o == obj)
				return true;
		}
		return false;
	}
	
	
	public static int[] addInt(int[] objs, int obj)
	{
		int[] result = new int[objs.length+1];
		int i = 0;
		for(int o : objs)
		{
			result[i++] = o;
		}
		result[objs.length] = obj;
		return result;
	}
	
	public static int[] removeInt(int[] objs, int obj)
	{
		List<Integer> temp = new ArrayList<Integer>();
		for(int i : objs)
		{
			if(i != obj)
			{
				temp.add(i);
			}
		}
		int[] result = new int[temp.size()];
		int j = 0;
		for(Integer o : temp)
		{
			result[j++] = o;
		}
		return result;
	}
	
	public static boolean isLongExist(long[] objs, long obj)
	{
		for(long o : objs)
		{
			if(o == obj)
				return true;
		}
		return false;
	}
	
	public static long[] addLong(long[] objs, int obj)
	{
		long[] result = new long[objs.length+1];
		int i = 0;
		for(long o : objs)
		{
			result[i++] = o;
		}
		result[objs.length] = obj;
		return result;
	}
	
	public static long[] removeLong(long[] objs, long obj)
	{
		List<Long> temp = new ArrayList<Long>();
		for(long i : objs)
		{
			if(i != obj)
			{
				temp.add(i);
			}
		}
		long[] result = new long[temp.size()];
		int j = 0;
		for(Long o : temp)
		{
			result[j++] = o;
		}
		return result;
	}
	

//	
//	public static void main(String[] args) {
//		int size = 100000;
//		Integer[] a = new Integer[size];
//		for(int i = 0; i < size; i++)
//		{
//			a[i] = i+1;
//		}
//		
//		int[] a1 = new int[size];
//		for(int i = 0; i < size; i++)
//		{
//			a1[i] = i+1;
//		}
//		
//		long t1 = System.currentTimeMillis();
//		Integer[] b = addObject(a, 4);
//		long t2 = System.currentTimeMillis();
//		long tt1 = t2 - t1;
//		System.out.println("tt1:"+tt1);
//
//		long t3 = System.currentTimeMillis();
//		int[] c = addInt(a1, 5);
//		long t4 = System.currentTimeMillis();
//		long tt2 = t4 - t3;
//		System.out.println("tt2:"+tt2);
//
//	}
	
	
}
