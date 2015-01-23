package org.springside.examples.quickstart.web;

import org.springside.examples.quickstart.entity.Order;
import org.springside.examples.quickstart.utils.NumberUtils;


/**
 * 使用drools的订单计价类.
 *
 * @author wgw
 */
public class OrderPricer {
	
	/**
	 * 订单打折的简便函数,供规则中使用.
	 */
	public void discount(Order order, double discount) {
		double newPrice = NumberUtils.divide(NumberUtils.multiply(order.getTotalPrice(), discount),100,2);
		order.setTotalPrice(newPrice);
	}

}
