package com.smartgroup.smartbilling.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartgroup.smartbilling.model.Bill;

public interface BillRepository extends JpaRepository<Bill, Long> {

}
