package com.zjlianhe.android.test;

/**
 * java静态代理原理实现
 * 静态代理缺点：每一个代理类只能为一个接口服务，这样一来程序开发中必然会产生过多的代理，而且，所有的代理操作除了调用的方法不一样之外，其他的操作都一样
 * ，则此时肯定是重复代码
 * <p>
 * 静态代理：由程序员创建或特定工具自动生成源代码，再对其编译。在程序运行前，代理类的.class文件就已经存在了。
 * <p>
 * 作用：
 * 
 * 1.主要用来做方法的增强，让你可以在不修改源码的情况下，增强一些方法
 * 
 * 2.可以用作远程调用，比如现在有Java接口，这个接口的实现部署在其它服务器上，在编写客户端代码的时候，没办法直接调用接口方法，
 * 因为接口是不能直接生成对象的，这个时候就可以考虑代理模式
 * 
 * @author zhangYB
 *
 */
public class StaticProxy {

	/**
	 * 第一步： 静态代理的主题类
	 * 
	 * 抽象主题角色，抽象主题类可以是抽象类，也可以是接口，是一个最普通的业务类型定义，无特殊要求
	 * 
	 * @author zhangYB
	 *
	 */
	public interface Subject {
		void operate();
	}

	/**
	 * 第二步： 具体主题角色，也叫被委托角色、被代理角色。是业务逻辑的具体执行者。
	 * 
	 * @author zhangYB
	 *
	 */
	public class SpecificSubject implements Subject {

		@Override
		public void operate() {
			System.err.println("静态代理主题类的具体实现类");
		}
	}

	/**
	 * 第三步： 主题角色的代理类,在不改变SpecificSubject类对象的前提下，实现远程调用具体类的操作
	 * 
	 * @author zhangYB
	 *
	 */
	public class Proxy implements Subject {

		private Subject mSubject;

		public Proxy(Subject subject) {
			this.mSubject = subject;
		}

		@Override
		public void operate() {// 此处在没有改变具体类的方法仍然可以做出相应的处理
			mSubject.operate();
		}
	}

	/**
	 * 客户端使用静态演示代码
	 */
	private void client() {
		// 这里不是直接使用具体角色类的方法,而是使用动态代理类的方法,
		Subject subject = new SpecificSubject();
		Proxy proxy = new Proxy(subject);
		proxy.operate();
	}
}
