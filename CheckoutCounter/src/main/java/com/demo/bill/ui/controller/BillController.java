package com.demo.bill.ui.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import com.demo.bill.service.BillCalculationService;
import com.demo.bill.ui.model.BillDTO;
import com.demo.bill.ui.model.BillRequestModel;
import com.demo.bill.ui.model.BillResponseModel;


@RestController
@RequestMapping("/counter")
public class BillController {

	@Autowired
	BillCalculationService billCalculationServiceImpl;

	@PostMapping(
			consumes = MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE
			)
	public BillResponseModel generateBill(@RequestBody BillRequestModel requestDetails){

		ModelMapper modelMapper = new ModelMapper(); 
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		BillDTO billDTO = billCalculationServiceImpl.generateBill(requestDetails);

		BillResponseModel response = modelMapper.map(billDTO, BillResponseModel.class);
		return response;
	}


}
