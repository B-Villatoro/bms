package com.smoothstack.borrower.BorrowerMicroService.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smoothstack.borrower.BorrowerMicroService.dao.BookCopyDao;
import com.smoothstack.borrower.BorrowerMicroService.dao.BookLoanDao;
import com.smoothstack.borrower.BorrowerMicroService.dao.BorrowerDao;
import com.smoothstack.borrower.BorrowerMicroService.entity.BookCopy;
import com.smoothstack.borrower.BorrowerMicroService.entity.BookLoan;
import com.smoothstack.borrower.BorrowerMicroService.entity.Borrower;

@Service
public class BorrowerService {

	@Autowired
	private BorrowerDao borrowerDao;
	@Autowired
	private BookLoanDao loanDao;
	@Autowired
	private BookCopyDao bookCopyDao;
	
	public List<BookLoan> getAllLoans() {
		return loanDao.getAllObjects();
	}

	public List<BookLoan> getBookLoans(int cardNumber) {
		return loanDao.getAllObjects(cardNumber);
	}

	public void deleteBookLoans(int bookId, int branchId, int cardNumber) {
		loanDao.deleteObjectWith(bookId, branchId, cardNumber);
		BookCopy bookCopy = bookCopyDao.getOneWith(bookId, branchId);
		bookCopy.setNoOfCopies(bookCopy.getNoOfCopies()+1);
		bookCopyDao.updateObject(bookCopy);
	}

	public BookLoan addBookLoan(int bookId, int branchId, int cardNumber) {
		BookLoan bookLoan = new BookLoan(bookId, branchId, cardNumber, dateOut(), dateDue(dateOut()));
		BookCopy bookCopy = bookCopyDao.getOneWith(bookId, branchId);
		bookCopy.setNoOfCopies(bookCopy.getNoOfCopies()-1);
		bookCopyDao.updateObject(bookCopy);		
		loanDao.createObject(bookLoan);
		return bookLoan;
	}

	public boolean validNumberOfCopies(BookLoan bookLoan) {
		BookCopy bookCopy = bookCopyDao.getOneWith(bookLoan.getBookId(), bookLoan.getBranchId());
		if (bookCopy.getNoOfCopies() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public BookLoan getOneBookLoan(int bookId, int branchId, int cardNumber) {
		return loanDao.getOneObject(bookId, branchId, cardNumber);
	}

	private Date dateOut() {
		LocalDate localDate = LocalDate.now(ZoneId.systemDefault());
		Date dateOut = java.sql.Date.valueOf(localDate);
		return dateOut;
	}

	private Date dateDue(Date date) {
		LocalDate localDate = LocalDate.parse(date.toString());
		Date dueDate = java.sql.Date.valueOf(localDate.plusDays(7));
		return dueDate;
	}

	public Boolean doesBorrowerHaveValidCard(int card) {
		List<Borrower> borrowers = new ArrayList<Borrower>();
		borrowers = borrowerDao.getAllObjects();
		int found = 0;
		for (Borrower borrower : borrowers) {
			if (borrower.getCardNo() == card) {
				found++;
			}
		}
		if (found > 0) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean bookCheckedOutAlready(int cardNumber, int branchId, int bookId) {
		List<BookLoan> loans = loanDao.getAllObjects(cardNumber);
		int found = 0;
		for (BookLoan loan : loans) {
			if (loan.getCardNo() == cardNumber & loan.getBookId() == bookId & loan.getBranchId() == branchId) {
				found++;
			}
		}
		if (found > 0) {
			return true;
		} else {
			return false;
		}
	}
}
