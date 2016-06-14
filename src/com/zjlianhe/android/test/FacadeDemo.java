package com.zjlianhe.android.test;

/**
 * 外观设计模式示例代码
 * 
 * @author zhangYB
 *
 */
public class FacadeDemo {

	/****** 接口类的定义 ****/
	public interface ServiceA {
		void methodA();
	}

	public interface ServiceB {
		void methodB();
	}

	public interface ServiceC {
		void methodC();
	}

	/************ 实现类的定义 ***************/
	public class ServiceAImpl implements ServiceA {

		@Override
		public void methodA() {
			// A服务类的具体实现
		}
	}

	public class ServiceBImpl implements ServiceB {

		@Override
		public void methodB() {
			// B服务类的具体实现
		}
	}

	public class ServiceCImpl implements ServiceC {

		@Override
		public void methodC() {
			// C服务类的具体实现
		}
	}

	/****** 外观设计模式 针对客户端的统一实现 ********/
	public class Facade {

		private ServiceAImpl aImpl;
		private ServiceBImpl bImpl;
		private ServiceCImpl cImpl;

		public Facade() {
			aImpl = new ServiceAImpl();
			bImpl = new ServiceBImpl();
			cImpl = new ServiceCImpl();
		}

		public void operate() {
			aImpl.methodA();
			bImpl.methodB();
			cImpl.methodC();
		}
	}

	// 外观设计模式将子系统的具体实现屏蔽，只对客户端提供包装好的方法
	public void client() {
		Facade facade = new Facade();
		facade.operate();
	}
}
