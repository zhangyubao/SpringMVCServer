package com.zjlianhe.android.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zjlianhe.android.bean.User;

public class JsonUtil {
	/**
	 * ObjectMapper是JSON操作的核心，Jackson的所有JSON操作都是在ObjectMapper中实现。
	 * ObjectMapper有多个JSON序列化的方法，可以把JSON字符串保存File、OutputStream等不同的介质中。
	 * writeValue(File arg0, Object arg1)把arg1转成json序列，并保存到arg0文件中。
	 * writeValue(OutputStream arg0, Object arg1)把arg1转成json序列，并保存到arg0输出流中。
	 * writeValueAsBytes(Object arg0)把arg0转成json序列，并把结果输出成字节数组。
	 * writeValueAsString(Object arg0)把arg0转成json序列，并把结果输出成字符串。
	 */
//
//	private static ObjectMapper mapper;
//
//	public String getObjectMapper(User user) {
//		if (mapper == null) {
//			synchronized (JsonUtil.class) {
//				if (mapper == null) {
//					mapper = new ObjectMapper();
//				}
//			}
//		}
////		String json = mapper.writeValueAsString(user);
//		return json;
//	}
}
