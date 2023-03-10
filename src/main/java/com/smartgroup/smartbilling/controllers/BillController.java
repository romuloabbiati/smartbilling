package com.smartgroup.smartbilling.controllers;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.smartgroup.smartbilling.model.Bill;
import com.smartgroup.smartbilling.model.BillStatus;
import com.smartgroup.smartbilling.repositories.BillRepository;
import com.smartgroup.smartbilling.repositories.filter.BillFilter;
import com.smartgroup.smartbilling.services.BillService;

@Controller
@RequestMapping("/bills")
public class BillController {
	
	private static final String REGISTER_VIEW = "Register";
	
	@Autowired
	private BillRepository billRepository;
	
	@Autowired
	private BillService billService;

	@RequestMapping("/new")
	public ModelAndView newBill() {
		ModelAndView modelAndView = new ModelAndView(REGISTER_VIEW);
		modelAndView.addObject(new Bill());
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid Bill bill, Errors errors, RedirectAttributes attributes) {
		if(errors.hasErrors()) {
			return REGISTER_VIEW;
		}
		try {
			billService.save(bill);
			attributes.addFlashAttribute("message", "The bill was saved successfully!");
			return "redirect:/bills/new";
		} catch (IllegalArgumentException e) {
			errors.reject("dueDate", null, e.getMessage());
			return REGISTER_VIEW;
		}
	}
	
	@RequestMapping
	@Transactional(readOnly = true)
	public ModelAndView search(@ModelAttribute("filter") BillFilter filter) {
		List<Bill> allBills = billService.filter(filter);
		
		ModelAndView modelAndView = new ModelAndView("Search");
		modelAndView.addObject("bills", allBills);

		return modelAndView;
	}
	
	@RequestMapping("/{id}")
	public ModelAndView edit(@PathVariable Long id) {
		Bill bill = billRepository.getOne(id);
		
		ModelAndView modelAndView = new ModelAndView(REGISTER_VIEW);
		modelAndView.addObject(bill);
		return modelAndView;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Long id, RedirectAttributes attributes) {
		billService.delete(id);
		
		attributes.addFlashAttribute("message", "Bill successfully deleted!");
		return "redirect:/bills";
	}
	
	@RequestMapping(value = "/{id}/receive", method = RequestMethod.PUT)
	public @ResponseBody String receive(@PathVariable Long id) {
		return billService.receive(id);
	}
	
	@ModelAttribute("allBillStatus")
	public List<BillStatus> allBillStatus() {
		return Arrays.asList(BillStatus.values());
	}
	
}
