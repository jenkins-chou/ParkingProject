package com.demo.utils;

import java.util.List;

public class CheckUtils {
	public static boolean checkArrayIsNotNull(List list){
		return list != null && list.size() > 0;
	}
}
