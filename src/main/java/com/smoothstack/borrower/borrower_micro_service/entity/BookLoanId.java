package com.smoothstack.borrower.borrower_micro_service.entity;

import java.io.Serializable;
import java.util.Iterator;

public class BookLoanId implements Iterable<BookLoanId>,Serializable{
	
	private static final long serialVersionUID = 1L;
		private int bookId;
		private int branchId;
		private int cardNo;
		
		public BookLoanId() {}
		
		public BookLoanId(int bookId, int branchId, int cardNo) {
			this.bookId = bookId;
			this.branchId = branchId;
			this.cardNo = cardNo;
		}

		public BookLoanId(int cardNumber) {
			this.cardNo = cardNumber;
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

		public int getCardNo() {
			return cardNo;
		}

		public void setCardNo(int cardNo) {
			this.cardNo = cardNo;
		}

		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			BookLoanId other = (BookLoanId) obj;
			if (bookId != other.bookId)
				return false;
			if (branchId != other.branchId)
				return false;
			if (cardNo != other.cardNo)
				return false;
			return true;
		}

		@Override
		public Iterator<BookLoanId> iterator() {
			// TODO Auto-generated method stub
			return null;
		}
		
}
