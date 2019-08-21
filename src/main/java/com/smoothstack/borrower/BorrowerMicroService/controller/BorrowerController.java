package com.smoothstack.borrower.BorrowerMicroService.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smoothstack.borrower.BorrowerMicroService.entity.BookLoan;
import com.smoothstack.borrower.BorrowerMicroService.service.BorrowerService;

@RestController
@RequestMapping(path = "/api/borrower")
public class BorrowerController {

	@Autowired
	private BorrowerService borrowerService;
	
	
	@GetMapping(value = "/bookloans", produces = "application/json")
	public ResponseEntity<List<BookLoan>> allLoans() {
		List<BookLoan> bookLoan = borrowerService.getAllLoans();
		if (bookLoan.isEmpty()) {
			return new ResponseEntity<List<BookLoan>>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<BookLoan>>(bookLoan, HttpStatus.OK);
		}
	}

	@GetMapping(value = "/{cardNumber}/bookloans", produces = "application/json")
	public ResponseEntity<List<BookLoan>> allCheckedOutBooks(@PathVariable("cardNumber") int cardNumber) {
		List<BookLoan> bookLoan = borrowerService.getBookLoansByCardNumber(cardNumber);
		
		if (bookLoan.isEmpty()) {
			return new ResponseEntity<List<BookLoan>>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<BookLoan>>(bookLoan, HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/{cardNumber}/branch/{branchId}/book/{bookId}", produces = "application/json")
	public ResponseEntity<BookLoan> oneCheckedOutBooks(@PathVariable("cardNumber") int cardNumber,
			@PathVariable("branchId") int branchId, @PathVariable("bookId") int bookId) {
		BookLoan bookLoan = borrowerService.getOneBookLoan(bookId, branchId, cardNumber);
		
		if (bookLoan == null) {
			return new ResponseEntity<BookLoan>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<BookLoan>(bookLoan, HttpStatus.OK);
		}
	}
	
	@PostMapping(value = "/{cardNumber}/branch/{branchId}/book/{bookId}")
	public ResponseEntity<BookLoan> checkOutBook(@PathVariable("cardNumber") int cardNumber,
			@PathVariable("branchId") int branchId, @PathVariable("bookId") int bookId) {
		boolean checkedOut = borrowerService.bookCheckedOutAlready(cardNumber, branchId, bookId);
		if (checkedOut) {
			return new ResponseEntity<BookLoan>(HttpStatus.CONFLICT);
		} else {
			borrowerService.addBookLoan(bookId, branchId, cardNumber);
			BookLoan bookLoan = new BookLoan(bookId, branchId, cardNumber);
			if(borrowerService.validNumberOfCopies(bookLoan)) {
				borrowerService.addBookLoan(bookId, branchId, cardNumber);
				return new ResponseEntity<BookLoan>(bookLoan, HttpStatus.CREATED);
			}else {
				return new ResponseEntity<BookLoan>(HttpStatus.FAILED_DEPENDENCY);
			}
		}
	}

	@PutMapping(value = "/{cardNumber}/branch/{branchId}/book/{bookId}")
	public ResponseEntity<BookLoan> extendDueDate(@PathVariable("cardNumber") int cardNumber,
			@PathVariable("branchId") int branchId, @PathVariable("bookId") int bookId) {
		return new ResponseEntity<BookLoan>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{cardNumber}/branch/{branchId}/book/{bookId}")
	public ResponseEntity<List<BookLoan>> returnCheckedOutBook(@PathVariable("cardNumber") int cardNumber,
			@PathVariable("branchId") int branchId, @PathVariable("bookId") int bookId) {
		boolean checkedOut = borrowerService.bookCheckedOutAlready(cardNumber, branchId, bookId);
		if (checkedOut) {
			borrowerService.deleteBookLoans(bookId, branchId, cardNumber);
			return new ResponseEntity<List<BookLoan>>(HttpStatus.OK);
		} else {
			return new ResponseEntity<List<BookLoan>>(HttpStatus.NOT_FOUND);
		}
	}
}
