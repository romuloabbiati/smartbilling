package com.smartgroup.smartbilling.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartgroup.smartbilling.model.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
	
	public List<Bill> findByDescriptionContaining(String description);
	
}
