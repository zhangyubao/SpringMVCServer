package com.zjlianhe.android.test.builder;

public class MainDemo {

	public static void main(String[] args) {
		InsuranceContract.ContractBuilder builder = new InsuranceContract.ContractBuilder(
				"9557", 123L, 456L);
		InsuranceContract insurance = builder.buildPersonName("小明")
				.buildOtherData("demo").build();
		insurance.doSomething();

	}
}
