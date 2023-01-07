package com.smartgroup.smartbilling.controllers;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
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
		modelAndView.addObject(new Bill());
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView save(@Valid Bill bill, Errors errors) {
		ModelAndView modelAndView = new ModelAndView("Register");
		if(errors.hasErrors()) {
			return modelAndView;
		}
		
		billRepository.save(bill);
		
		modelAndView.addObject("message", "The bill was saved successfully!");
		return modelAndView;
	}
	
	@RequestMapping
	@Transactional(readOnly = true)
	public ModelAndView search() {
		List<Bill> allBills = billRepository.findAll();
		
		ModelAndView modelAndView = new ModelAndView("Search");
		modelAndView.addObject("bills", allBills);

		return modelAndView;
	}
	
//	@RequestMapping
//	public String search() {
//		return "Search";
//	}
	
	@ModelAttribute("allBillStatus")
	public List<BillStatus> allBillStatus() {
		return Arrays.asList(BillStatus.values());
	}
	
}
