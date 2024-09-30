package com.reactive.entity.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.reactive.entity.BookModel;

@Repository
public interface BookRepository extends ReactiveCrudRepository<BookModel, Long>{

}
