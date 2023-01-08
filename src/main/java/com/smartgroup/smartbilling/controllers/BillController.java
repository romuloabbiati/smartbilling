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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String save(@Valid Bill bill, Errors errors, RedirectAttributes attributes) {
		ModelAndView modelAndView = new ModelAndView("Register");
		if(errors.hasErrors()) {
			return "Register";
		}
		billRepository.save(bill);
		attributes.addFlashAttribute("message", "The bill was saved successfully!");
		return "redirect:/bills/new";
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
