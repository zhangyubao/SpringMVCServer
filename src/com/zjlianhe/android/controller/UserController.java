package com.zjlianhe.android.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zjlianhe.android.bean.User;

@Controller
// ���й����û��Ľӿڵ�url��ַ
@RequestMapping("/UserController")
public class UserController {

	// �������ӿڷ���json����
	// ע�� ֻҪ����@ResponseBodyע�� Ĭ�Ϸ��صľ���json ������Ҫ��ת��ҳ������
	// ����ͨ��ModelAndView �����ƶ�Ҫ���ص�ҳ����ͼ������

	/**
	 * ����tomcat�������������⣺ 1��ʹ���ⲿ��װ��tomcat ���������õ� ��Servers��ͼ�� �Ҽ��Ƴ���ǰ�ڷ����������е���Ŀ
	 * �ڷ��������Ҽ�--->clean-->˫��������-->����Ϊʹ���ⲿ��װ�ķ�����
	 * 
	 * 
	 * 2��SpringMVC��ܵ����� ע��<url-pattern> ������ ���������Ϊ��������Ӧ�Ĳ�ͬ��׺��������
	 * ����ȫ�������ص�������Controller ȥ����
	 * 
	 * 3��������Ŀ��ʱ�� �������Ƕ�̬web��Ŀ ��Dynamic web Project �����뾲̬��Ŀ������
	 */
	@RequestMapping("/user")
	public @ResponseBody String getUser() {
		String json = null;
		try {
			User user = new User();
			user.setNickName("zaoshanghao");
			user.setUserName("zhangsan");
			user.setPassWord("zhangyubao");
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(user);
			System.err.println("receive the request ~~~~~~~~~~~~" + json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}

	@RequestMapping("/users")
	public String getUserJsp(Model model) {
		String retVal = "user";
		User user = new User();
		user.setNickName("nickName");
		model.addAttribute("user", user);
		return retVal;
	}
}
