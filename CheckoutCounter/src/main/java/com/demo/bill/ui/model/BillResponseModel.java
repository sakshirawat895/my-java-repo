package com.demo.bill.ui.model;

import java.util.List;

public class BillResponseModel {

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
