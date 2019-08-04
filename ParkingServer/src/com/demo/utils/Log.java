package com.demo.utils;

public class Log {
	
	public static final String defaultLogText = "There is no log to output !!";
	
	public static final void i(String info){
		System.out.println(checkTextNotNull(info)?info:defaultLogText);
	}
	
	public static final void i(int num){
		System.out.println(num+"");
	}
	
	public static final boolean checkTextNotNull(String text){
		return text != null && !text.equals("");
	}

}
