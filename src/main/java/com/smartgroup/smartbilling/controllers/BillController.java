package com.smartgroup.smartbilling.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.smartgroup.smartbilling.model.Bill;

@Controller
@RequestMapping("/bills")
public class BillController {

	@RequestMapping("/new")
	public String newBill() {
		return "Register";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String save(Bill bill) {
		System.out.println(">>>>>>" + bill.getDescription());
		
		return "Register";
	}
	
}
