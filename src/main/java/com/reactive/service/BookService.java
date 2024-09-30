package com.reactive.service;

import com.reactive.entity.BookModel;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookService {

	public Mono<BookModel> createBook(BookModel bookModel);
	
	public Flux<BookModel> getAll();
	
	public Mono<BookModel> getBook(Long id);
	
	public Mono<BookModel> updateBook(BookModel bookModel);
	
	public Mono<Void> deleteBook(Long id);
	
	public Mono<BookModel> searchBook(String query);
	
}
