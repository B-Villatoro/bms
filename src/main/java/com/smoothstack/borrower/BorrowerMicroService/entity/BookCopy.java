package com.smoothstack.borrower.BorrowerMicroService.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity@IdClass(BookCopyId.class)
@Table(name="tbl_book_copies")
public class BookCopy {
	@Id
	private int bookId;
	@Id
	private int branchId;
	@Column
	private int noOfCopies;
	
	public BookCopy() {
		
	}
	
	public BookCopy(int bookId, int branchId, int noOfCopies) {
		this.bookId = bookId;
		this.branchId = branchId;
		this.noOfCopies = noOfCopies;
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

	public int getNoOfCopies() {
		return noOfCopies;
	}

	public void setNoOfCopies(int noOfCopies) {
		this.noOfCopies = noOfCopies;
	}

	@Override
	public String toString() {
		return "BookCopy info: [bookId=" + bookId + ", branchId=" + branchId + ", noOfCopies=" + noOfCopies + "]"+"\n";
	}
	
	
	
}
