package com.smoothstack.borrower.borrower_micro_service.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smoothstack.borrower.borrower_micro_service.entity.BookCopy;
import com.smoothstack.borrower.borrower_micro_service.entity.BookCopyId;

@Repository
public interface BookCopyDao extends JpaRepository<BookCopy,BookCopyId>{
}
