package com.demo.bill.ui.model;


public class ItemPurchasedVo{


	private String itemName;
	private String status;
	private int quantity;
	private double price;
	private double totalPrice;
	
	public ItemPurchasedVo() {
		
	}
	
	public ItemPurchasedVo(String itemName, String status, int quantity, double price, double totalPrice) {
		this.itemName = itemName;
		this.status = status;
		this.quantity = quantity;
		this.price = price;
		this.totalPrice = totalPrice;
	}
	
	@Override
	public String toString() {
		return "ItemPurchasedVo [itemName=" + itemName + ", status=" + status + ", quantity=" + quantity + ", price="
				+ price + ", totalPrice=" + totalPrice + "]";
	}

	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
	
}
