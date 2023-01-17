package com.smartgroup.smartbilling.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartgroup.smartbilling.model.Bill;
import com.smartgroup.smartbilling.model.BillStatus;
import com.smartgroup.smartbilling.repositories.BillRepository;
import com.smartgroup.smartbilling.repositories.filter.BillFilter;

@Service
public class BillService {

	@Autowired
	private BillRepository billRepository;
	
	@Transactional
	public void save(Bill bill) {
		try {
			billRepository.save(bill);
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Invalid date format!");
		}
	}
	
	public void delete(Long id) {
		billRepository.deleteById(id);
	}
	
	@Transactional
	public String receive(Long id) {
		Bill bill = billRepository.getOne(id);
		bill.setStatus(BillStatus.PAID);
		billRepository.save(bill);
		return BillStatus.PAID.getDescription();
	}
	
	@Transactional(readOnly = true)
	public List<Bill> filter (BillFilter filter) {
		String description = filter.getDescription() == null ? "" : filter.getDescription();
		return billRepository.findByDescriptionContaining(description);
	}
	
}
