package com.zjlianhe.android.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
// ��һ���������������ַӳ���ע�⣬��������򷽷��ϡ��������ϣ���ʾ���е�������Ӧ����ķ��������Ըõ�ַ��Ϊ��·��
public class HomeController {
 
	// value�� ָ�������ʵ�ʵ�ַ��
	// method�� ָ�������method���ͣ� GET��POST��PUT��DELETE��;
	// @RequestMapping(value = "/hello.json", method = RequestMethod.GET)
	// ��ע�����ڶ�ȡRequest�����body�������ݣ�ʹ��ϵͳĬ�����õ�HttpMessageConverter���н�����Ȼ�����Ӧ�����ݰ󶨵�Ҫ���صĶ����ϣ�
	// @ResponseBody
	// ��ô����Ҫ���ʴ˷����� ����·��Ӧ����http://ip��ַ:�˿�/SpringMVC/HelloController/hello.json
	/**
	 * ��ҳ ������ /views/home.jspҳ��
	 * */
	@RequestMapping("index")
	public String toHome() {
		System.out.println("let`s  go!");
		return "home";
	}

	@RequestMapping("index1")
	public ModelAndView toHome1() {
		// ����ģ�ͺ���ͼ,������Ⱦҳ��.��ָ��Ҫ���ص�ҳ��Ϊhome1
		ModelAndView mv = new ModelAndView();
		System.out.println("let`s  go1!");
		mv.setViewName("home1");
		return mv;
	}
}
