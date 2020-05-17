package com.demo.pament.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test; 
import org.junit.runner.RunWith; 
import org.mockito.InjectMocks; 
import org.mockito.Mock; 
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize; 


import com.demo.bill.data.ItemEntity;
import com.demo.bill.data.ItemRepository;
import com.demo.bill.data.TaxRateEntity;
import com.demo.bill.data.TaxRateRepository;
import com.demo.bill.service.BillCalculationServiceImpl;
import com.demo.bill.ui.model.BillRequestModel;
import com.demo.bill.ui.model.ItemPurchasedVo;
import com.demo.bill.ui.model.ItemsVo;

@RunWith(MockitoJUnitRunner.class)
public class BillCalculationServiceTest {
	
	List<ItemPurchasedVo> expected = new ArrayList<ItemPurchasedVo>();
	List<ItemsVo> items = new ArrayList<ItemsVo>();
	BillRequestModel billR= new BillRequestModel();

	@InjectMocks 
	BillCalculationServiceImpl billCalculationService;
	
	@Mock
	ItemRepository itemRepository;
	
	@Mock
	TaxRateRepository taxRateRepository;
	
	
	@Test
	public void calculateTotalAmountTest() {
		List<ItemPurchasedVo> purchasedItem = new ArrayList<ItemPurchasedVo>();
		purchasedItem.add(new ItemPurchasedVo("CAT","Unavailable",0,0,0));
		purchasedItem.add(new ItemPurchasedVo("HANGER","Available",2,650,1300.00));
		
		assertEquals(1300, billCalculationService.calculateTotalAmount(purchasedItem),0); 		
	}
	
	@Test
	public void calculateBillTest() {
		
		
		expected.add(new ItemPurchasedVo("PANT","Available",2,3500.0,7700.0));
		List<ItemsVo> items = new ArrayList<ItemsVo>();
		items.add(new ItemsVo("PANT",2));
		
		BillRequestModel billR= new BillRequestModel();
		billR.setItemDetails(items);
		
		
		ItemEntity item = new ItemEntity();
		item.setItem_name("PANT");
		item.setPrice(3500);
		item.setCategory("A");
		Optional<ItemEntity> optionalItemEntity = Optional.of(item);
		when(itemRepository.findById("PANT")).thenReturn(optionalItemEntity);
		
		TaxRateEntity taxRate = new TaxRateEntity();
		taxRate.setCategory("A");
		taxRate.setTax(10);
		Optional<TaxRateEntity> optionalTaxEntity = Optional.of(taxRate);
		when(taxRateRepository.findById("A")).thenReturn(optionalTaxEntity);
		
		
		List<ItemPurchasedVo> result = billCalculationService.calculateBill(billR);
		assertThat(billCalculationService.calculateBill(billR), hasSize(1));
		assertTrue(result.get(0).getItemName().equals(expected.get(0).getItemName()));
		assertTrue(result.get(0).getStatus().equals(expected.get(0).getStatus()));
		assertEquals(result.get(0).getQuantity(), expected.get(0).getQuantity());
		assertEquals(result.get(0).getPrice(), expected.get(0).getPrice(),0);
		assertEquals(result.get(0).getTotalPrice(), expected.get(0).getTotalPrice(),0);
	}


}
