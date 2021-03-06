package org.springside.examples.quickstart.entity;


/**
 * 订单项
 *
 * @author wgw
 */

public class OrderItem {
	private Order order;

	private Product product;

	private Integer quantity;

	private Double unitprice;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(Double unitprice) {
		this.unitprice = unitprice;
	}

	/**
	 * 价格小计
	 */
	public double getTotalPrice() {
		return quantity * unitprice;
	}
}
