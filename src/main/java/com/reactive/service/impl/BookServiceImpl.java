package com.reactive.service.impl;

import org.springframework.stereotype.Service;

import com.reactive.entity.BookModel;
import com.reactive.entity.repository.BookRepository;
import com.reactive.exception.AppException;
import com.reactive.service.BookService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

	private final BookRepository bookRepository;

	@Override
	public Mono<BookModel> createBook(BookModel bookModel) {
		try {
			int no=2%0;
			return bookRepository.save(bookModel);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(500, "Internal server error");
		}
	}

	@Override
	public Flux<BookModel> getAll() {
		try {
			return bookRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(500, "Internal server error");
		}
	}

	@Override
	public Mono<BookModel> getBook(Long id) {
		try {
			return bookRepository.findById(Mono.just(id));
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(500, "Internal server error");
		}
	}

	@Override
	public Mono<BookModel> updateBook(BookModel bookModel) {
		try {
			Mono<BookModel> book = bookRepository.findById(bookModel.getBookId());
			return book.flatMap(data -> {
				data.setAuthor(bookModel.getAuthor());
				data.setBookName(bookModel.getBookName());
				data.setDescription(bookModel.getDescription());
				data.setPublisher(bookModel.getPublisher());
				return bookRepository.save(data);
			});
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(500, "Internal server error");
		}
	}

	@Override
	public Mono<Void> deleteBook(Long id) {
		try {
			return bookRepository.findById(id).flatMap(book -> bookRepository.delete(book));
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(500, "Internal server error");
		}
	}

	@Override
	public Mono<BookModel> searchBook(String query) {
		// TODO Auto-generated method stub
		return null;
	}

}
