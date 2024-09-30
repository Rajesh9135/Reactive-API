package com.reactive.controller;

import java.time.Duration;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reactive.entity.BookModel;
import com.reactive.service.BookService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
public class BookController {

	private final BookService bookService;
	
	@PostMapping
	public Mono<BookModel> createBook(@Valid @RequestBody BookModel bookModel){
		return bookService.createBook(bookModel);
	}
	
	@GetMapping
	public Flux<BookModel> getAll(){
		return bookService.getAll().delayElements(Duration.ofSeconds(2));
	}
	
	@GetMapping("/{bookId}")
	public Mono<BookModel> getBookById(@PathVariable Long bookId){
		return bookService.getBook(bookId);
	}
	
	@PutMapping
	public Mono<BookModel> updateBook(@RequestBody BookModel bookModel){
		return bookService.updateBook(bookModel);
	}
	
	@DeleteMapping("/{bookId}")
	public Mono<Void> deleteBookById(@PathVariable Long bookId){
		return bookService.deleteBook(bookId);
	}
}
