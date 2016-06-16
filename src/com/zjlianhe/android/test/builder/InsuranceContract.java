package com.zjlianhe.android.test.builder;

/**
 * 使用建造模式构建复杂对象
 * 
 * 　　考虑这样一个实际应用，要创建一个保险合同的对象，里面很多属性的值都有约束，要求创建出来的对象是满足这些约束规则的。约束规则比如
 * ：保险合同通常情况下可以和个人签订
 * ，也可以和某个公司签订，但是一份保险合同不能同时与个人和公司签订。这个对象里有很多类似这样的约束，采用建造模式来构建复杂的对象
 * ，通常会对建造模式进行一定的简化，因为目标明确，就是创建某个复杂对象，因此做适当简化会使程序更简洁。大致简化如下：
 * 
 * 　　●　　由于是用Builder模式来创建某个对象，因此就没有必要再定义一个Builder接口，直接提供一个具体的建造者类就可以了。
 * 
 * 　　●　　对于创建一个复杂的对象，可能会有很多种不同的选择和步骤，干脆去掉“导演者”，把导演者的功能和Client的功能合并起来，也就是说,
 * Client这个时候就相当于导演者，它来指导构建器类去构建需要的复杂对象。
 * 
 * @author zhangYB
 *
 */

public class InsuranceContract {
	// 保险合同编号
	private String contractId;
	/**
	 * 被保险人员的名称，同一份保险合同，要么跟人员签订，要么跟公司签订 也就是说，“被保险人员”和“被保险公司”这两个属性，不可能同时有值
	 */
	private String personName;
	// 被保险公司的名称
	private String companyName;
	// 保险开始生效日期
	private long beginDate;
	// 保险失效日期，一定会大于保险开始生效日期
	private long endDate;
	// 其他数据
	private String otherData;

	private InsuranceContract(ContractBuilder builder) {
		this.contractId = builder.contractId;
		this.personName = builder.personName;
		this.companyName = builder.companyName;
		this.beginDate = builder.beginDate;
		this.endDate = builder.endDate;
		this.otherData = builder.otherData;
	}

	/**
	 * 一些其他的操作
	 */
	public void doSomething() {
		System.out.println("保险的其他操作");
	}

	/**
	 * 
	 * 这是一个静态内部类
	 * 
	 * @author zhangYB
	 *
	 */
	public static class ContractBuilder {
		private String contractId;
		private String personName;
		private String companyName;
		private long beginDate;
		private long endDate;
		private String otherData;

		/**
		 * 构造方法
		 * 
		 * @param contractId
		 * @param beginDate
		 * @param endDate
		 */
		public ContractBuilder(String contractId, long beginDate, long endDate) {
			this.contractId = contractId;
			this.beginDate = beginDate;
			this.endDate = endDate;
		}

		// 被保险人名称
		public ContractBuilder buildPersonName(String personName) {
			this.personName = personName;
			return this;
		}

		// 被保险公司名称
		public ContractBuilder buildcompanyName(String companyName) {
			this.companyName = companyName;
			return this;
		}

		// 构建其他数据
		public ContractBuilder buildOtherData(String otherData) {
			this.otherData = otherData;
			return this;
		}

		public InsuranceContract build() {

			if (contractId == null || contractId.trim().length() == 0) {
				throw new IllegalArgumentException("合同编号不能为空");
			}

			boolean signPerson = (personName != null && personName.trim()
					.length() > 0);
			boolean signCompany = (companyName != null && companyName.trim()
					.length() > 0);

			if (signPerson && signCompany) {
				throw new IllegalArgumentException("一份保险合同不能同时与个人和公司签订");
			}
			if (signPerson == false && signCompany == false) {
				throw new IllegalArgumentException("一份保险合同不能没有签订对象");
			}
			if (beginDate <= 0) {
				throw new IllegalArgumentException("一份保险合同必须有开始生效的日期");
			}
			if (endDate <= 0) {
				throw new IllegalArgumentException("一份保险合同必须有失效的日期");
			}
			if (endDate < beginDate) {
				throw new IllegalArgumentException("一份保险合同的失效日期必须大于生效日期");
			}

			return new InsuranceContract(this);
		}
	}
}
