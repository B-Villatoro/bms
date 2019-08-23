package com.smoothstack.borrower.borrower_micro_service.entity;



import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity@IdClass(BookLoanId.class)
@Table(name="tbl_book_loans")
public class BookLoan {
	@Id
	@Column(name="bookId")
	private int bookId;
	@Id
	@Column(name="branchId")
	private int branchId;
	@Id
	@Column(name="cardNo")
	private int cardNo;
	
	@Column(name="dateOut")
	private Date dateOut;
	
	@Column(name="dueDate")
	private Date dueDate;
	
	public BookLoan() {}
	
	public BookLoan(int bookId, int branchId, int cardNo, Date dateOut, Date dueDate) {
		this.bookId = bookId;
		this.branchId = branchId;
		this.cardNo = cardNo;
		this.dateOut = dateOut;
		this.dueDate = dueDate;
	}
	
	public BookLoan(int bookId, int branchId, int cardNo) {
		this.bookId = bookId;
		this.branchId = branchId;
		this.cardNo = cardNo;
	}

	public int getBookId() {
		return bookId;
	}

	public int getBranchId() {
		return branchId;
	}

	public int getCardNo() {
		return cardNo;
	}

	public Date getDateOut() {
		return dateOut;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public void setCardNo(int cardNo) {
		this.cardNo = cardNo;
	}

	public void setDateOut(Date dateOut) {
		this.dateOut = dateOut;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	@Override
	public String toString() {
		return "BookLoan info: [bookId=" + bookId + ", branchId=" + branchId + ", cardNo=" + cardNo + ", dateOut=" + dateOut
				+ ", dueDate=" + dueDate + "]"+"\n";
	}
}

