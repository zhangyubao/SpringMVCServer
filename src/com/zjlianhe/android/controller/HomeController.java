package com.zjlianhe.android.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
// 是一个用来处理请求地址映射的注解，可用于类或方法上。用于类上，表示类中的所有响应请求的方法都是以该地址作为父路径
public class HomeController {
 
	// value： 指定请求的实际地址；
	// method： 指定请求的method类型， GET、POST、PUT、DELETE等;
	// @RequestMapping(value = "/hello.json", method = RequestMethod.GET)
	// 该注解用于读取Request请求的body部分数据，使用系统默认配置的HttpMessageConverter进行解析，然后把相应的数据绑定到要返回的对象上；
	// @ResponseBody
	// 那么我们要访问此方法是 完整路径应该是http://ip地址:端口/SpringMVC/HelloController/hello.json
	/**
	 * 首页 返回至 /views/home.jsp页面
	 * */
	@RequestMapping("index")
	public String toHome() {
		System.out.println("let`s  go!");
		return "home";
	}

	@RequestMapping("index1")
	public ModelAndView toHome1() {
		// 创建模型和视图,用于渲染页面.并指向要返回的页面为home1
		ModelAndView mv = new ModelAndView();
		System.out.println("let`s  go1!");
		mv.setViewName("home1");
		return mv;
	}
}
