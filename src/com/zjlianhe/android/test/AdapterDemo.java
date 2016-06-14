package com.zjlianhe.android.test;

/**
 * java 适配器模式
 * <p>
 * 一、类适配器模式
 * <p>
 * 二、对象适配器模式
 * <p>
 * 三、缺省适配器模式
 * <p>
 * 
 * @author zhangYB
 *
 */
public class AdapterDemo {

	/**
	 * 类适配器模式示例代码
	 */
	// 源角色
	public class Adaptee {

		public void method_1() {

		}
	}
	// 目标角色
	public interface Target {
		void method_1();

		void method_2();

		void method_3();

		void method_4();
	}
	public abstract class DefaultAdapter implements Target {

		@Override
		public void method_1() {

		}

		@Override
		public void method_2() {

		}

		@Override
		public void method_3() {

		}

		@Override
		public void method_4() {

		}
	}
	// 在此处用到那个方法就重写那个方法即可
	public class TargetAdapter extends DefaultAdapter {

	}

	public class ObjectAdapter {

		private Adaptee adaptee;

		public ObjectAdapter(Adaptee adaptee) {
			this.adaptee = adaptee;
		}

		// 委派机制
		public void method_1() {
			this.adaptee.method_1();
		}

		public void method_2() {
			// 相应的操作代码
		}
	}

	// 适配器 adapter Target是目标角色就是我们想要拥有的方法
	public class Adapter extends Adaptee implements Target {

		@Override
		public void method_2() {
			// 通过适配器模式 在适配器Adapter中添加目标想要实现的方法
		}

		@Override
		public void method_3() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void method_4() {
			// TODO Auto-generated method stub
			
		}

	}

}
