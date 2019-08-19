package com.smoothstack.borrower.BorrowerMicroService.entity;

import java.sql.Date;

public class BookLoan {
	
	private int bookId;
	private int branchId;
	private int cardNo;
	private Date dateOut;
	private Date dueDate;
	
	public BookLoan() {
		
	}
	
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
