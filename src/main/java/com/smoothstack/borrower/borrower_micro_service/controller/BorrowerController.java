package com.smoothstack.borrower.borrower_micro_service.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.smoothstack.borrower.borrower_micro_service.entity.BookLoan;
import com.smoothstack.borrower.borrower_micro_service.service.BorrowerService;

@RestController
@CrossOrigin(origins = "http://localhost:5500")
@RequestMapping(path = "/api/borrower")
public class BorrowerController {

	Logger logger = LoggerFactory.getLogger(BorrowerController.class);
	
	@Autowired
	private BorrowerService borrowerService;
	
	

	@RequestMapping(value = "/bookloans", method = RequestMethod.GET, produces = { "application/xml",
			"application/json" })
	public ResponseEntity<List<BookLoan>> allLoans() {
		List<BookLoan> bookLoan = borrowerService.getAllLoans();
		if (bookLoan.isEmpty()) {
			logger.info("Http status bad");
			return new ResponseEntity<List<BookLoan>>(HttpStatus.NOT_FOUND);
		} else {
			logger.info("Http status good");
			return new ResponseEntity<List<BookLoan>>(bookLoan, HttpStatus.OK);
		}
	}

	@GetMapping(value = "/{cardNumber}/bookloans", produces = { "application/xml", "application/json" })
	public ResponseEntity<List<BookLoan>> allCheckedOutBooks(@PathVariable("cardNumber") int cardNumber) {
		List<BookLoan> bookLoan = borrowerService.getBookLoansByCardNumber(cardNumber);

		if (bookLoan.isEmpty()) {
			return new ResponseEntity<List<BookLoan>>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<BookLoan>>(bookLoan, HttpStatus.OK);
		}
	}

	@PostMapping(value = "/bookloan/checkout", produces = { "application/xml", "application/json" }, consumes = {
			"application/x-www-form-urlencoded", "application/xml", "application/json" })
	public ResponseEntity<BookLoan> checkOutBook(@RequestBody BookLoan bookLoan) {

		boolean checkedOut = borrowerService.bookCheckedOutAlready(bookLoan);
		if (checkedOut) {
			return new ResponseEntity<BookLoan>(HttpStatus.CONFLICT);

		} else if (borrowerService.validNumberOfCopies(bookLoan)) {
			bookLoan = borrowerService.checkOutBook(bookLoan);

			logger.info("Book checked out "+ bookLoan);
			return new ResponseEntity<BookLoan>(bookLoan, HttpStatus.CREATED);

		} else {
			logger.info("Book failed to be checked out");
			return new ResponseEntity<BookLoan>(HttpStatus.FAILED_DEPENDENCY);
		}
	}

	@PutMapping(value = "/bookloan/extend", produces = { "application/xml", "application/json" }, consumes = {
			"application/xml", "application/json" })
	public ResponseEntity<BookLoan> extendDueDate(@RequestBody BookLoan bookLoan) {
		return new ResponseEntity<BookLoan>(HttpStatus.OK);
	}

	@RequestMapping(value = "/bookloan/returnbook/book/{bookId}/branch/{branchId}/cardno/{cardNumber}", method = RequestMethod.DELETE)
	public HttpStatus returnCheckedOutBook(@PathVariable("bookId") int bookId, @PathVariable("branchId") int branchId,
			@PathVariable("cardNumber") int cardNumber) {
		BookLoan bookLoan = new BookLoan(bookId, branchId, cardNumber);
		boolean checkedOut = borrowerService.bookCheckedOutAlready(bookLoan);
		if (checkedOut) {
			borrowerService.deleteBookLoans(bookLoan);
			logger.info("Book Loan Returned bookid: "+bookId+" branchid: "+branchId+" cardno: "+cardNumber);
			return HttpStatus.OK;
		} else {
			logger.info("Failed to return book");
			return HttpStatus.NOT_FOUND;
		}
	}

	// Debugging methods

	// Debug
	@GetMapping(value = "/{cardNumber}/branch/{branchId}/book/{bookId}")
	public ResponseEntity<BookLoan> oneCheckedOutBooks(@PathVariable("cardNumber") int cardNumber,
			@PathVariable("branchId") int branchId, @PathVariable("bookId") int bookId) {
		BookLoan bookLoan = borrowerService.getOneBookLoan(bookId, branchId, cardNumber);

		if (bookLoan == null) {
			return new ResponseEntity<BookLoan>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<BookLoan>(bookLoan, HttpStatus.OK);
		}
	}

}
