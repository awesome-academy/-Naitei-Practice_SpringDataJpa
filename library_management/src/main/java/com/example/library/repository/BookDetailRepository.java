package com.example.library.repository;

import com.example.library.entity.BookDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDetailRepository extends JpaRepository<BookDetail,Long> {
    List<BookDetail> findByDescriptionContaining(String keyword);
}
