package com.demo.bill.ui.model;


public class ItemsVo{

	public ItemsVo() {
		
	}
	public ItemsVo(String itemName, int quantity) {
		super();
		this.itemName = itemName;
		this.quantity = quantity;
	}
	private String itemName;
	private int quantity;
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
