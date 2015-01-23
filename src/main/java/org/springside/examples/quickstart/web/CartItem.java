package org.springside.examples.quickstart.web;

import org.springside.examples.quickstart.entity.Product;
import org.springside.examples.quickstart.utils.NumberUtils;



/**
 * 购物车项目.
 *
 * @author wgw
 */
public class CartItem {
	private Product product = null;

	private Integer quantity = 0;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/**
	 * 增加数量.
	 */
	public void incrementQuantity() {
		quantity += 1;
	}

	/**
	 * 价格小计.
	 */
	public double getTotalPrice() {
		if (product == null)
			return 0;
		return NumberUtils.multiply(quantity, product.getUnitprice());
	}

}
