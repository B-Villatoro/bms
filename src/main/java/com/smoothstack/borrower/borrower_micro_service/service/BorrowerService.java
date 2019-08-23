package com.smoothstack.borrower.borrower_micro_service.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smoothstack.borrower.borrower_micro_service.dao.BookCopyDao;
import com.smoothstack.borrower.borrower_micro_service.dao.BookLoanDao;
import com.smoothstack.borrower.borrower_micro_service.entity.BookCopy;
import com.smoothstack.borrower.borrower_micro_service.entity.BookCopyId;
import com.smoothstack.borrower.borrower_micro_service.entity.BookLoan;
import com.smoothstack.borrower.borrower_micro_service.entity.BookLoanId;

@Service
public class BorrowerService {

	@Autowired
	private BookLoanDao loanDao;
	@Autowired
	private BookCopyDao bookCopyDao;

	public List<BookLoan> getAllLoans() {
		return loanDao.findAll();
	}

	public List<BookLoan> getBookLoansByCardNumber(int cardNumber) {
		return loanDao.findByCardNumber(cardNumber);
	}
	
	public BookLoan checkOutBook(BookLoan bookLoan) {
		BookLoan checkOut = new BookLoan(bookLoan.getBookId(), bookLoan.getBranchId(), bookLoan.getCardNo(), dateOut(), dateDue(dateOut()));
		Optional<BookCopy> bookCopy = bookCopyDao.findById(new BookCopyId(checkOut.getBookId(), checkOut.getBranchId()));
		bookCopy.get().setNoOfCopies(bookCopy.get().getNoOfCopies() - 1);
		bookCopyDao.save(bookCopy.get());
		loanDao.save(checkOut);
		return checkOut;
	}

	public void deleteBookLoans(BookLoan bookLoan) {
		BookLoanId bl = new BookLoanId(bookLoan.getBookId(), bookLoan.getBranchId(), bookLoan.getCardNo());
		loanDao.deleteById(bl);
		Optional<BookCopy> bookCopy = bookCopyDao.findById(new BookCopyId(bookLoan.getBookId(), bookLoan.getBranchId()));
		bookCopy.get().setNoOfCopies(bookCopy.get().getNoOfCopies() + 1);
		bookCopyDao.save(bookCopy.get());
	}

	public BookLoan addBookLoan(int bookId, int branchId, int cardNumber) {
		BookLoan bookLoan = new BookLoan(bookId, branchId, cardNumber, dateOut(), dateDue(dateOut()));
		Optional<BookCopy> bookCopy = bookCopyDao.findById(new BookCopyId(bookId, branchId));
		bookCopy.get().setNoOfCopies(bookCopy.get().getNoOfCopies() - 1);
		bookCopyDao.save(bookCopy.get());
		loanDao.save(bookLoan);
		return bookLoan;
	}

	public boolean validNumberOfCopies(BookLoan bookLoan) {
		Optional<BookCopy> bookCopy = bookCopyDao.findById(new BookCopyId(bookLoan.getBookId(), bookLoan.getBranchId()));
		if (bookCopy.get().getNoOfCopies() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public BookLoan getOneBookLoan(BookLoan bookLoan) {
		BookLoanId bli = new BookLoanId(bookLoan.getBookId(), bookLoan.getBranchId(), bookLoan.getCardNo());
		return loanDao.findById(bli).get();
	}

	public BookLoan getOneBookLoan(int bookId, int branchId, int cardNumber) {
		BookLoanId bli = new BookLoanId(bookId, branchId, cardNumber);
		return loanDao.findById(bli).get();
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
	
	public Boolean bookCheckedOutAlready(BookLoan bookLoan) {
		Optional<BookLoan> boolbookLoan = loanDao.findById(new BookLoanId(bookLoan.getBookId(), bookLoan.getBranchId(), bookLoan.getCardNo()));
		return boolbookLoan.isPresent();
	}

	

//	public Boolean doesBorrowerHaveValidCard(int card) {
//		List<Borrower> borrowers = new ArrayList<Borrower>();
//		borrowers = borrowerDao.getAllObjects();
//		int found = 0;
//		for (Borrower borrower : borrowers) {
//			if (borrower.getCardNo() == card) {
//				found++;
//			}
//		}
//		if (found > 0) {
//			return true;
//		} else {
//			return false;
//		}
//	}

	
}
