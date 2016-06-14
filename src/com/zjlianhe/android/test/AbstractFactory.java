package com.zjlianhe.android.test;

import org.springframework.context.annotation.Bean;

/**
 * 工厂设计模式：简单工厂设计模式、工厂方法模式、抽象工厂模式
 * 
 * @author zhangYB
 *
 */
public class AbstractFactory {

	public static void main(String[] args) {

	}

	/****************************** 简单工厂模式 start ************************************/
	// 抽象类
	public abstract class BMW {

	}

	public class BMW320 extends BMW {
		public BMW320() {
			System.out.println("创建320");
		}
	}

	public class BMW523 extends BMW {
		public BMW523() {
			System.out.println("创建523");
		}
	}

	public class Factory {
		// 此处的方法应该定义成静态方法
		public BMW creatCar(int flag) {
			switch (flag) {
			case 320:
				return new BMW320();
			case 523:
				return new BMW523();
			default:
				break;
			}
			return null;
		}
	}

	// 简单工厂客户端使用
	public void simpleCarClient() {
		Factory factory = new Factory();
		BMW bmw320 = factory.creatCar(320);
		BMW bmw532 = factory.creatCar(532);
	}

	/****************************** 简单工厂模式end ************************************/

	/****************************** 工厂方法模式start ************************************/
	public interface BMWFactory {
		BMW creatBMW();
	}

	public class BWM320Factory implements BMWFactory {

		@Override
		public BMW creatBMW() {
			return new BMW320();
		}
	}

	public class BWM532Factory implements BMWFactory {

		@Override
		public BMW creatBMW() {
			return new BMW523();
		}
	}

	// 工厂方法客户端使用
	public void FactoryMethodClient() {
		BWM320Factory factory = new BWM320Factory();
		factory.creatBMW();
		BWM532Factory factory2 = new BWM532Factory();
		factory.creatBMW();
	}

	/****************************** 工厂方法模式 end ************************************/

	/****************************** 抽象工厂模式start ************************************/
	// 抽象工厂、具体工厂、抽象产品和具体产品
	// 抽象产品 ProductA ProductB两种产品属于同一族
	public interface ProductA {
		void method_1();

		void method_2();
	}

	public interface ProductB {
		void method_1();

		void method_2();
	}

	// 抽象工厂既可以生产ProductA 又可以生产ProductB
	abstract class ProdctFactory {
		abstract ProductA factoryA();

		abstract ProductB factoryB();
	}

	// 等级为1的具体A类产品RealProductA_1
	class RealProductA_1 implements ProductA {

		@Override
		public void method_1() {
			System.out.println("等级为1的A类真实产品RealProductA_1的method_1");
		}

		@Override
		public void method_2() {
			System.out.println("等级为1的A类真实产品RealProductA_1的method_2");
		}
	}

	// 等级为2的具体A类产品RealProductA_2
	class RealProductA_2 implements ProductA {

		@Override
		public void method_1() {
			System.out.println(" 等级为2的A类真实产品RealProductA_2的method_1");
		}

		@Override
		public void method_2() {
			System.out.println(" 等级为2的A类真实产品RealProductA_2的method_2");
		}
	}

	// 等级为1的具体B类产品RealProductB_1
	class RealProductB_1 implements ProductB {

		@Override
		public void method_1() {
			System.out.println(" 等级为1的B类真实产品RealProductA_2的method_1");
		}

		@Override
		public void method_2() {
			System.out.println(" 等级为1的B类真实产品RealProductA_2的method_2");
		}
	}

	// 等级为2具体B类产品RealProductB_2
	class RealProductB_2 implements ProductB {

		@Override
		public void method_1() {
			System.out.println("等级为2的B类真实产品RealProductA_2的method_1");
		}

		@Override
		public void method_2() {
			System.out.println("等级为2的B类真实产品RealProductA_2的method_2");
		}
	}

	// 改工厂只生产等级为1的产品
	public class CreateAClassProductFactory extends ProdctFactory {

		@Override
		ProductA factoryA() {
			return new RealProductA_1();
		}

		@Override
		ProductB factoryB() {
			return new RealProductB_1();
		}

	}

	// 改工厂只生产等级为2的产品
	public class CreateBClassProductFactory extends ProdctFactory {

		@Override
		ProductA factoryA() {
			return new RealProductA_2();
		}

		@Override
		ProductB factoryB() {
			return new RealProductB_2();
		}
	}

	public void abstractFactoryClient() {
		CreateAClassProductFactory factory = new CreateAClassProductFactory();
		ProductA a = factory.factoryA();
		a.method_1();
		a.method_2();
		ProductB b = factory.factoryB();
		b.method_1();
		b.method_2();

		CreateBClassProductFactory factory2 = new CreateBClassProductFactory();
		ProductA a2 = factory2.factoryA();
		a2.method_1();
		a2.method_2();
		ProductB b2 = factory2.factoryB();
		b2.method_1();
		b2.method_2();
	}
	/****************************** 抽象工厂模式 end ************************************/

}
