package com.demo.bill.ui.model;

import java.io.Serializable;
import java.util.List;

public class BillDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3518126268436545589L;

	private double totalAmountToPay;
	private List<ItemPurchasedVo> items;
	
	public List<ItemPurchasedVo> getItems() {
		return items;
	}
	public void setItems(List<ItemPurchasedVo> items) {
		this.items = items;
	}
	public double getTotalAmountToPay() {
		return totalAmountToPay;
	}
	public void setTotalAmountToPay(double totalAmountToPay) {
		this.totalAmountToPay = totalAmountToPay;
	}
	}
