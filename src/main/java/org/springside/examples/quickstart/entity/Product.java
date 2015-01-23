package org.springside.examples.quickstart.entity;

import javax.persistence.*;

import org.apache.commons.lang.StringUtils;

/**
 * 产品
 *
 * @author wgw
 */

public class Product {
	private static final int SHORT_DESCN_LENGTH = 200;


	private Integer id;


	private Integer inventory;

	private String name;

	private String descn;

	private Double unitprice=0.0;

	private String status;

	private String shortDescn = null;


	public String getDescn() {
		return this.descn;
	}

	public Integer getId() {
		return id;
	}

	public Integer getInventory() {
		return this.inventory;
	}

	public String getName() {
		return this.name;
	}

	
	public String getShortDescn() {
		if (shortDescn == null) {
			shortDescn = StringUtils.substring(descn, 0, SHORT_DESCN_LENGTH) + "...";
		}
		return shortDescn;
	}

	public String getStatus() {
		return this.status;
	}

	public Double getUnitprice() {
		return this.unitprice;
	}


	public void setDescn(String descn) {
		this.descn = descn;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setShortDescn(String shortDescn) {
		this.shortDescn = shortDescn;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setUnitprice(Double unitprice) {
		this.unitprice = unitprice;
	}
}
