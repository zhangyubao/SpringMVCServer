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
// 所有关于用户的接口的url地址
@RequestMapping("/UserController")
public class UserController {

	// 服务器接口返回json数据
	// 注意 只要加上@ResponseBody注解 默认返回的就是json 而不是要跳转的页面名称
	// 可以通过ModelAndView 对象制定要返回的页面视图的名称

	/**
	 * 关于tomcat服务器配置问题： 1、使用外部安装的tomcat 而不是内置的 在Servers视图下 右键移除当前在服务器上运行的项目
	 * 在服务器上右键--->clean-->双击服务器-->更改为使用外部安装的服务器
	 * 
	 * 
	 * 2、SpringMVC框架的配置 注意<url-pattern> 的配置 这个配置是为了拦截相应的不同后缀名的请求
	 * 可以全部都拦截掉，交给Controller 去处理
	 * 
	 * 3、创建项目的时候 创建的是动态web项目 即Dynamic web Project 区分与静态项目的区别
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
