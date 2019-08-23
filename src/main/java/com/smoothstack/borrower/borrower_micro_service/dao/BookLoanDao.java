package com.smoothstack.borrower.borrower_micro_service.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.smoothstack.borrower.borrower_micro_service.entity.BookLoan;
import com.smoothstack.borrower.borrower_micro_service.entity.BookLoanId;

@Repository
public interface BookLoanDao extends JpaRepository<BookLoan, BookLoanId> {
	@Query("SELECT bl FROM BookLoan bl WHERE (bl.cardNo) = :cardNo")
	List<BookLoan> findByCardNumber(int cardNo);
}
