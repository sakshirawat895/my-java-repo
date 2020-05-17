package com.demo.bill.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.bill.data.ItemEntity;
import com.demo.bill.data.ItemRepository;
import com.demo.bill.data.TaxRateEntity;
import com.demo.bill.data.TaxRateRepository;
import com.demo.bill.ui.model.BillDTO;
import com.demo.bill.ui.model.BillRequestModel;
import com.demo.bill.ui.model.BillResponseModel;
import com.demo.bill.ui.model.ItemPurchasedVo;
import com.demo.bill.ui.model.ItemsVo;

@Service
public class BillCalculationServiceImpl implements BillCalculationService {
	
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	TaxRateRepository taxRateRepository;

@Override
public List<ItemPurchasedVo> calculateBill(BillRequestModel requestDetails) {
	List<ItemsVo> itemList = requestDetails.getItemDetails();
	List<ItemPurchasedVo> itemPurchased = new ArrayList<ItemPurchasedVo>();
			
			itemList.stream()
			.forEach(index-> {
				int quantity= index.getQuantity();
				Optional<ItemEntity> itemEntity= itemRepository.findById(index.getItemName());
				if(itemEntity.isPresent()) {
				double price = itemEntity.get().getPrice();

				Optional<TaxRateEntity> taxRate = taxRateRepository.findById(itemEntity.get().getCategory());
				int tax= taxRate.get().getTax();

				double amount = (price*quantity*tax/100) + (price*quantity);
				
				itemPurchased.add(new ItemPurchasedVo(index.getItemName().toUpperCase(),"Available", quantity, price, amount));
				} else {
					itemPurchased.add(new ItemPurchasedVo(index.getItemName().toUpperCase(),"Unavailable", 0, 0, 0));
				}
			})

			;
	
	
	return itemPurchased;
}


@Override
public double calculateTotalAmount(List<ItemPurchasedVo> purchasedItems) {
	
	double amount = purchasedItems.stream()
					.mapToDouble(index->index.getTotalPrice())
					.sum();
	return amount;
}

public BillDTO generateBill(BillRequestModel requestDetails) {
	List<ItemPurchasedVo> purchasedItem = calculateBill(requestDetails);
	double amount = calculateTotalAmount(purchasedItem);
	BillDTO dto =new BillDTO();
	dto.setItems(purchasedItem);
	dto.setTotalAmountToPay(amount);
	return dto;
}

}
