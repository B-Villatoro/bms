package com.smoothstack.borrower.BorrowerMicroService.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.smoothstack.borrower.BorrowerMicroService.entity.BookCopy;

@Component
public class BookCopyDao implements ObjectDao<BookCopy> {

	@Value("${DB_URL}")
	private String dbUrl;
	@Value("${DB_UN}")
	private String user;
	@Value("${DB_PASS}")
	private String pass;

	public Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Error with database connection.");
			e.printStackTrace();
		}
		Connection connection = null;
		try {
			connection = (Connection) DriverManager.getConnection(dbUrl, user, pass);
		} catch (SQLException e) {
			System.out.println("Error with database connection.");
			e.printStackTrace();
		}
		return connection;
	}

	@Override
	public List<BookCopy> getAllObjects() {
		List<BookCopy> copies = new ArrayList<BookCopy>();
		try {
			String allCopiesQuery = "select * from tbl_book_copies;";
			PreparedStatement myStatement = getConnection().prepareStatement(allCopiesQuery);
			ResultSet myResultSet = myStatement.executeQuery(allCopiesQuery);
			while (myResultSet.next()) {
				int resultOne = myResultSet.getInt("bookId");
				int resultTwo = myResultSet.getInt("branchId");
				int resultThree = myResultSet.getInt("noOfCopies");
				BookCopy temp = new BookCopy(resultOne, resultTwo, resultThree);
				copies.add(temp);

			}
		} catch (SQLException e) {
			System.out.println("Error connecting to database.");
		}

		return copies;
	}

	public BookCopy getOneWith(int bookId, int branchId) {
		BookCopy bookCopy = null;
		try {
			String oneCopyQuery = "select * from tbl_book_copies WHERE bookId=? AND branchId=?";
			PreparedStatement myStatement = getConnection().prepareStatement(oneCopyQuery);
			myStatement.setInt(1, bookId);
			myStatement.setInt(2, branchId);
			ResultSet myResultSet = myStatement.executeQuery();
			
			while (myResultSet.next()) {
				int resultOne = myResultSet.getInt("bookId");
				int resultTwo = myResultSet.getInt("branchId");
				int resultThree = myResultSet.getInt("noOfCopies");
				bookCopy = new BookCopy(resultOne, resultTwo, resultThree);
			}
		} catch (SQLException e) {
			System.out.println("Error connecting to database.");
			e.printStackTrace();
		}

		return bookCopy;

	}

	@Override
	public void createObject(BookCopy copy) {
		try {
			String addNewCopy = "insert into tbl_book_copies(bookId, branchId, noOfCopies) values(?,?,?)";
			PreparedStatement myStatement = getConnection().prepareStatement(addNewCopy);
			myStatement.setInt(1, copy.getBookId());
			myStatement.setInt(2, copy.getBranchId());
			myStatement.setInt(3, copy.getNoOfCopies());
			myStatement.executeUpdate();
			System.out.println("Copy successfully added");
		} catch (SQLException e) {
			System.out.println("Error connecting to database.");
		}
	}

	@Override
	public void deleteObject(int id) {
		try {
			String deleteCopy = "delete from tbl_book_copies where bookId=?";
			PreparedStatement myStatement = getConnection().prepareStatement(deleteCopy);
			myStatement.setInt(1, id);
			myStatement.executeUpdate();
			System.out.println("Copy successfully deleted");
		} catch (SQLException e) {
			System.out.println("Error connecting to database.");
		}

	}

	@Override
	public void updateObject(BookCopy copy) {
		try {
			String updateCopy = "update tbl_book_copies set noOfCopies=? where bookId=? and branchId=?";
			PreparedStatement myStatement = getConnection().prepareStatement(updateCopy);
			myStatement.setInt(1, copy.getNoOfCopies());
			myStatement.setInt(2, copy.getBookId());
			myStatement.setInt(3, copy.getBranchId());
			myStatement.executeUpdate();
			System.out.println("Copy successfully updated.");
		} catch (SQLException e) {
			System.out.println("Error connecting to database.");
		}

	}

	public List<BookCopy> getAmountOfCopies(BookCopy copy) {
		List<BookCopy> copies = new ArrayList<BookCopy>();
		try {
			String amountOfCopies = "SELECT noOfCopies FROM tbl_book_copies WHERE bookId=? AND branchId=?";
			PreparedStatement myStatement = getConnection().prepareStatement(amountOfCopies);
			myStatement.setInt(1, copy.getBookId());
			myStatement.setInt(2, copy.getBranchId());
			ResultSet myResultSet = myStatement.executeQuery();
			while (myResultSet.next()) {
				int resultOne = myResultSet.getInt("bookId");
				int resultTwo = myResultSet.getInt("branchId");
				int resultThree = myResultSet.getInt("noOfCopies");
				BookCopy temp = new BookCopy(resultOne, resultTwo, resultThree);
				copies.add(temp);
			}
		} catch (SQLException e) {
			System.out.println("Error connecting to database.");
		}

		return copies;
	}

}
