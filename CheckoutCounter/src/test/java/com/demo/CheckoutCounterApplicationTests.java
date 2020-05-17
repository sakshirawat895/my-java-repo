package com.demo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.bill.ui.model.BillRequestModel;
import com.demo.bill.ui.model.ItemsVo;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class CheckoutCounterApplicationTests {
	
	@Autowired  
	private TestRestTemplate restTemplate; 

	@Test
	public void contextLoads() {
		BillRequestModel requestModel = new BillRequestModel();
		List<ItemsVo> items = new ArrayList<ItemsVo>();
		items.add(new ItemsVo("CAT",2));
		items.add(new ItemsVo("HANGER",2));
		
		requestModel.setItemDetails(items);
		
		ResponseEntity<String> responseEntity = this.restTemplate
	            .postForEntity("/counter", requestModel, String.class);
		
		String expected = "{\"totalAmountToPay\":1300.0,\"items\":[{\"itemName\":\"CAT\",\"status\":\"Unavailable\",\"quantity\":0,\"price\":0.0,\"totalPrice\":0.0},{\"itemName\":\"HANGER\",\"status\":\"Available\",\"quantity\":2,\"price\":650.0,\"totalPrice\":1300.0}]}";
		
		assertEquals(200, responseEntity.getStatusCodeValue());
		

		assertEquals(expected,
				responseEntity.getBody().toString());
	}

	
}
