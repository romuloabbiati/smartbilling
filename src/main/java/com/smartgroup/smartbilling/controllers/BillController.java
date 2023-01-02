package com.smartgroup.smartbilling.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.smartgroup.smartbilling.model.Bill;
import com.smartgroup.smartbilling.repositories.BillRepository;

@Controller
@RequestMapping("/bills")
public class BillController {
	
	@Autowired
	private BillRepository billRepository;

	@RequestMapping("/new")
	public String newBill() {
		return "Register";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String save(Bill bill) {
		billRepository.save(bill);
		return "Register";
	}
	
}
