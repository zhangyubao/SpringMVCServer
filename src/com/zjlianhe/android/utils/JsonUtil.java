package com.zjlianhe.android.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zjlianhe.android.bean.User;

public class JsonUtil {
	/**
	 * ObjectMapper��JSON�����ĺ��ģ�Jackson������JSON����������ObjectMapper��ʵ�֡�
	 * ObjectMapper�ж��JSON���л��ķ��������԰�JSON�ַ�������File��OutputStream�Ȳ�ͬ�Ľ����С�
	 * writeValue(File arg0, Object arg1)��arg1ת��json���У������浽arg0�ļ��С�
	 * writeValue(OutputStream arg0, Object arg1)��arg1ת��json���У������浽arg0������С�
	 * writeValueAsBytes(Object arg0)��arg0ת��json���У����ѽ��������ֽ����顣
	 * writeValueAsString(Object arg0)��arg0ת��json���У����ѽ��������ַ�����
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
