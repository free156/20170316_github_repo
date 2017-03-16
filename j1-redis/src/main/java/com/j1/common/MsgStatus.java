package com.j1.common;

public enum MsgStatus
{
	/**
	 * 发生未知异常
	 */
	EXCEPTION("1"), 
	/**
	 * 正常返回
	 */
	NORMAL("0"), 
	/**
	 * 未找到结果
	 */
	NO_RESULT("3"), 
	/**
	 * 参数错误
	 */
	PARAMS_ERROR("2");
	
	

	private String i;

	private MsgStatus(String n)
	{
		this.i = n;
	}

	public String getCode()
	{
		return this.i;
	}

	public static MsgStatus resolve(String i)
	{
		try
		{
			return MsgStatus.valueOf(i);
		}
		catch (Exception e)
		{
			return null;
		}
	}

	public static String getTypeName(String type)
	{
		try
		{
			return MsgStatus.resolve(type).getCode();
		}
		catch (Exception e)
		{
			return "";
		}
	}
	
	public static void main(String[] args)
	{
		System.out.println(MsgStatus.EXCEPTION.getCode());
	}

}
