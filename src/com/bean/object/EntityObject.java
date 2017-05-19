package com.bean.object;

import java.lang.reflect.Field;

public class EntityObject
{
	public static final long serialVersionUID = 1L;
	
	public String getVariable(String key) {
		try {
			Field field = getClass().getDeclaredField(key);
			field.setAccessible(true);//如果是私有的则需要设置这个
			Class valueClass = field.getType();
			if (valueClass == Integer.TYPE)
				return String.valueOf(field.getInt(this));
			if (valueClass == Boolean.TYPE)
				return String.valueOf(field.getBoolean(this));
			if (valueClass == Long.TYPE)
				return String.valueOf(field.getLong(this));
			if (valueClass == Double.TYPE)
				return String.valueOf(field.getDouble(this));
			return (String) field.get(this);
		} catch (Exception e) {
			e.printStackTrace();
			return "0";
		}
	}
	

	public static EntityObject cloneObject(EntityObject eo)
	{
		try
		{
			String clsName = eo.getClass().getName();
			EntityObject obj = (EntityObject)Class.forName(clsName).newInstance();
			eo.copyTo(obj);
			return obj;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		return null;
	}
	
	public void copyTo(EntityObject obj)
	{
		
	}
	

	public boolean setVariable(String key, String value) {
		try {
			if (value.length() == 0)
				return false;
			Field field = getClass().getDeclaredField(key);
			field.setAccessible(true);//如果是私有的则需要设置这个
			
			Class valueClass = field.getType();
			if (valueClass == Integer.TYPE)
				field.setInt(this, Integer.parseInt(value));
			else if (valueClass == Short.TYPE)
				field.setShort(this, Short.parseShort(value));
			else if (valueClass == Float.TYPE)
				field.setFloat(this, Float.parseFloat(value));
			else if (valueClass == Long.TYPE)
				field.set(this, Long.parseLong(value));
			else if (valueClass == Double.TYPE)
				field.set(this, Double.parseDouble(value));
			else 
				field.set(this, value);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
