package org.springside.examples.quickstart.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springside.examples.quickstart.entity.OrderItem;
import org.springside.examples.quickstart.entity.Product;


/**
 * 购物车.
 *
 * @author wgw
 */
@SuppressWarnings("unchecked")
public class Cart {
	private final Map itemMap = Collections.synchronizedMap(new HashMap());

	private List<CartItem> items = new ArrayList<CartItem>();

	/**
	 * 取得所有item.
	 */
	public List<CartItem> getitems() {
		return items;
	}

	/**
	 * 取得item数量.
	 */
	public int getSize() {
		return items.size();
	}

	/**
	 * 取得价格合计.
	 */
	public Double getSubTotal() {
		Double subTotal = (double) 0;
		for (CartItem cartItem : items) {
			Product item = cartItem.getProduct();
			subTotal += item.getUnitprice() * cartItem.getQuantity();
		}
		return subTotal;
	}

	/**
	 * 放入购物车.
	 */
	public void addItem(Product product) {
		CartItem cartItem = (CartItem) itemMap.get(product.getId());
		if (cartItem == null) {
			cartItem = new CartItem();
			cartItem.setProduct(product);
			cartItem.setQuantity(0);
			itemMap.put(product.getId(), cartItem);
			items.add(cartItem);
		}
		cartItem.incrementQuantity();
	}

	/**
	 * 使用购物车中的内容初始化订单项.
	 */
	public List<OrderItem> getOrderItems() {
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		for (CartItem cartItem : items) {
			OrderItem item = new OrderItem();
			item.setProduct(cartItem.getProduct());
			item.setQuantity(cartItem.getQuantity());
			item.setUnitprice(cartItem.getProduct().getUnitprice());
			orderItems.add(item);
		}
		return orderItems;
	}
	
    /**
     * 清空购物车.
     */
	public void clear(){
		items.clear();
		itemMap.clear();
	}
}
