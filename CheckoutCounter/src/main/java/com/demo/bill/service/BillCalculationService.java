package com.demo.bill.service;

import java.util.List;

import com.demo.bill.ui.model.BillDTO;
import com.demo.bill.ui.model.BillRequestModel;
import com.demo.bill.ui.model.ItemPurchasedVo;

public interface BillCalculationService {

	 List<ItemPurchasedVo> calculateBill(BillRequestModel requestDetails);
	 double calculateTotalAmount(List<ItemPurchasedVo> purchasedItems);
	 BillDTO generateBill(BillRequestModel requestDetails);
	
}
