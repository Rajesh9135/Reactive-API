package com.reactive.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

@Table("book")
@Data
@Accessors(chain = true)
public class BookModel {

	@Id
	@Column("id")
	private Long bookId;
	
	@Column("book_name")
	@NotBlank(message = "Book name cannot be null or empty")
	private String bookName;
	
	@Column("publisher")
	@NotBlank(message = "Publisher cannot be null or empty")
	private String publisher;
	
	@Column("book_desc")
	@NotBlank(message = "Book Description cannot be null or empty")
	private String description;
	
	@Column("author")
	@NotBlank(message = "Book author cannot be null or empty")
	private String author;
	
}
