package com.zjlianhe.android.test;

/**
 * 策略模式示例代码
 * 
 * Note: 策略模式只是对算法的封装
 * 
 * @author zhangYB
 *
 */
public class StrategyDemo {

	public interface Strategy {
		// 计算价格的算法
		double calculatePrice(double price);
	}

	public class PrimaryMember implements Strategy {

		@Override
		public double calculatePrice(double price) {
			return price;// 初级会员没有优惠
		}
	}

	public class MiddleMember implements Strategy {

		@Override
		public double calculatePrice(double price) {
			return price * 0.9;// 中级会员九折
		}
	}

	public class VIPMember implements Strategy {

		@Override
		public double calculatePrice(double price) {
			return price * 0.85; // VIP会员八五折
		}
	}

	// 策略模式的包装,方便客户端使用
	public class Price {

		private Strategy strategy;

		public Price(Strategy strategy) {
			this.strategy = strategy;
		}

		/**
		 * 计算实际价格即折后价
		 * 
		 * @param price
		 * @return
		 */
		public double getActualPrice(double price) {
			return this.strategy.calculatePrice(price);
		}
	}

	// 客户端使用
	public void client() {
		Strategy strategy = new MiddleMember();
		Price price = new Price(strategy);
		price.getActualPrice(300);
	}

}
