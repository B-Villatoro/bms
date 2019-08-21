package com.smoothstack.borrower.BorrowerMicroService.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smoothstack.borrower.BorrowerMicroService.entity.BookCopy;
import com.smoothstack.borrower.BorrowerMicroService.entity.BookCopyId;

@Repository
public interface BookCopyDao extends JpaRepository<BookCopy,BookCopyId>{
}
