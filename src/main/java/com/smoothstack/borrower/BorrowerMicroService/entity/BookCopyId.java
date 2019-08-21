package com.smoothstack.borrower.BorrowerMicroService.entity;

import java.io.Serializable;

public class BookCopyId implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int bookId;
	private int branchId;
	public BookCopyId(int bookId, int branchId) {
		super();
		this.bookId = bookId;
		this.branchId = branchId;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

}
