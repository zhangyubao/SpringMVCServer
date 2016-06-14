package com.zjlianhe.android.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.springframework.aop.framework.ProxyCreatorSupport;

/**
 * java 动态代理的实现原理
 * 
 * 一个典型的动态代理创建对象过程可分为以下四个步骤：
 * 
 * 1、通过实现InvocationHandler接口创建自己的调用处理器 IvocationHandler handler = new
 * InvocationHandlerImpl(...);
 * 
 * 2、通过为Proxy类指定ClassLoader对象和一组interface创建动态代理类 Class clazz =
 * Proxy.getProxyClass(classLoader,new Class[]{...});
 * 
 * 3、通过反射机制获取动态代理类的构造函数，其参数类型是调用处理器接口类型 Constructor constructor =
 * clazz.getConstructor(new Class[]{InvocationHandler.class});
 * 
 * 4、通过构造函数创建代理类实例，此时需将调用处理器对象作为参数被传入 Interface Proxy =
 * (Interface)constructor.newInstance(new Object[] (handler));
 * 为了简化对象创建过程，Proxy类中的newInstance方法封装了2~4，只需两步即可完成代理对象的创建。
 * 生成的ProxySubject继承Proxy类实现Subject接口
 * ，实现的Subject的方法实际调用处理器的invoke方法，而invoke方法利用反射调用的是被代理对象的的方法（Object
 * result=method.invoke(proxied,args)）
 * 
 * 动态代理的缺点：
 * 
 * Proxy已经设计得非常优美，但是还是有一点点小小的遗憾之处，那就是它始终无法摆脱仅支持interface代理的桎梏，因为它的设计注定了这个遗憾。
 * 回想一下那些动态生成的代理类的继承关系图
 * ，它们已经注定有一个共同的父类叫Proxy。Java的继承机制注定了这些动态代理类们无法实现对class的动态代理，原因是多继承在Java中本质上就行不通
 * 
 * @author zhangYB
 *
 */
public class DynamicProxy {

	/**
	 * 动态代理的主体类
	 * 
	 * @author zhangYB
	 *
	 */
	public interface Subject {
		void operate();
	}

	/**
	 * 动态代理的具体实现类
	 * 
	 * @author zhangYB
	 *
	 */
	public static class RealSubject implements Subject {

		@Override
		public void operate() {
			// 具体实现类想要做的操作
			System.out.println("具体实现类想要做的操作");
		}
	}

	public static class ProxyHandler implements InvocationHandler {

		private Object mObject;

		// 获取该类的所有接口
		// mObject.getClass().getInterfaces();
		public ProxyHandler(Object object) {
			this.mObject = object;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			// 在转调具体目标对象之前，可以执行一些功能处理
			System.out.println("调用目标函数之前");
			System.out.println(method.getName());
			// 转调具体目标对象的方法
			Object result = method.invoke(mObject, args);

			System.out.println("调用目标函数之后");
			// 在转调具体目标对象之后，可以执行一些功能处理

			return result;
		}
	}

	private static class CglibProxy implements MethodInterceptor {

		@Override
		public Object intercept(Object object, Method method, Object[] args,
				MethodProxy methodProxy) throws Throwable {
			System.out.println("++++++before " + methodProxy.getSuperName()
					+ "++++++");
			System.out.println(method.getName());
			Object result = methodProxy.invokeSuper(object, args);
			System.out.println("++++++before " + methodProxy.getSuperName()
					+ "++++++");
			return result;
		}
	}

	public static void main(String[] args) {
		// JDK动态代理实现方式
		Subject subject = new RealSubject();
		Subject proxy = (Subject) Proxy.newProxyInstance(
				Subject.class.getClassLoader(), new Class[] { Subject.class },
				new ProxyHandler(subject));
		proxy.operate();

		// Cglib动态代理实现方式
		CglibProxy cglibProxy = new CglibProxy();
		Enhancer enhancer = new Enhancer();
		enhancer.setCallback(cglibProxy);
		enhancer.setSuperclass(RealSubject.class);
		Subject create = (Subject) enhancer.create();
		create.operate();
	}
}
