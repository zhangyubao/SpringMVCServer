package com.zjlianhe.android.test.builder;

/**
 * 简单建造者模式示例代码
 * 
 * @author zhangYB
 *
 */
public class Builder_1_Demo {

	/**
	 * 产品对象
	 * 
	 * @author zhangYB
	 *
	 */
	public class Product {
		// 产品有两个部分,定义一些产品的操作
		private String partOne;
		private String partTwo;

		public String getPartOne() {
			return partOne;
		}

		public void setPartOne(String partOne) {
			this.partOne = partOne;
		}

		public String getPartTwo() {
			return partTwo;
		}

		public void setPartTwo(String partTwo) {
			this.partTwo = partTwo;
		}
	}

	/**
	 * 产品对象的构建接口
	 * 
	 * @author zhangYB
	 *
	 */
	public interface Builder {
		// 建造产品第一部分
		void buildPartOne();

		// 建造产品第二部分
		void buildPartTwo();

		// 返回建造的对象
		Product build();
	}

	/**
	 * 具体的产品构建类
	 * 
	 * @author zhangYB
	 *
	 */
	public class CocreateProduct implements Builder {

		private Product product = new Product();

		@Override
		public void buildPartOne() {
			System.out.println("构建第一部分");
			product.setPartOne("构建第一部分");
		}

		@Override
		public void buildPartTwo() {
			System.out.println("构建第二部分");
			product.setPartTwo("构建第二部分");
		}

		@Override
		public Product build() {

			return product;
		}
	}

	/**
	 * 导演者
	 * 
	 * @author zhangYB
	 *
	 */
	public class Director {

		private Builder builder;

		public Director(Builder builder) {
			this.builder = builder;
		}

		public void construct() {
			builder.buildPartOne();
			builder.buildPartTwo();
		}
	}

	/**
	 * 客户端
	 */
	public void Client() {
		Builder builder = new CocreateProduct();
		Director director = new Director(builder);
		director.construct();
		Product product = builder.build();
		System.out.println(product.getPartOne());
		System.out.println(product.getPartTwo());
	}
}
