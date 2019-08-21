package com.smoothstack.borrower.BorrowerMicroService.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smoothstack.borrower.BorrowerMicroService.entity.Borrower;

@Repository
public interface BorrowerDao extends JpaRepository<Borrower, Integer>{
}
