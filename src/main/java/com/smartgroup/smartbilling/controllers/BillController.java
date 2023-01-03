package com.smartgroup.smartbilling.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.smartgroup.smartbilling.model.Bill;
import com.smartgroup.smartbilling.model.BillStatus;
import com.smartgroup.smartbilling.repositories.BillRepository;

@Controller
@RequestMapping("/bills")
public class BillController {
	
	@Autowired
	private BillRepository billRepository;

	@RequestMapping("/new")
	public ModelAndView newBill() {
		ModelAndView modelAndView = new ModelAndView("Register");
		modelAndView.addObject("allBillStatus", BillStatus.values());
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView save(Bill bill) {
		billRepository.save(bill);
		
		ModelAndView modelAndView = new ModelAndView("Register");
		modelAndView.addObject("message", "The bill was saved successfully!");
		return modelAndView;
	}
	
	@ModelAttribute("allBillStatus")
	public List<BillStatus> allBillStatus() {
		return Arrays.asList(BillStatus.values());
	}
	
}
