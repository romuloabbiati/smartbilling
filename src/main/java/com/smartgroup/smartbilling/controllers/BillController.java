package com.smartgroup.smartbilling.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BillController {

	@RequestMapping(path = "/bills/new")
	public String newBill() {
		return "Register";
	}
	
}
