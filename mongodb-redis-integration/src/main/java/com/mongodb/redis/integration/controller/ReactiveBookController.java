/*
 * Copyright 2015-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mongodb.redis.integration.controller;

import javax.validation.Valid;

import com.mongodb.redis.integration.document.Book;
import com.mongodb.redis.integration.reactiveservice.ReactiveBookService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * MongoDB Redis Integration Reactive Controller Book class.
 *
 * @author Raja Kolli
 *
 */
@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class ReactiveBookController {

	private final ReactiveBookService reactiveBookService;

	@GetMapping("/title/{title}")
	public Mono<Book> getBookByTitle(@PathVariable(name = "title") String title) {
		return this.reactiveBookService.findByTitle(title);
	}

	/**
	 * <p>
	 * getAllBooks.
	 * </p>
	 * @return a {@link reactor.core.publisher.Flux} object.
	 */
	@GetMapping
	public Flux<Book> getAllBooks() {
		return this.reactiveBookService.findAllBooks();
	}

	/**
	 * <p>
	 * getBookById.
	 * </p>
	 * @param bookId a {@link java.lang.String} object.
	 * @return a {@link reactor.core.publisher.Mono} object.
	 */
	@GetMapping("/{id}")
	public Mono<ResponseEntity<Book>> getBookById(@PathVariable("id") String bookId) {
		return this.reactiveBookService.getBookById(bookId);
	}

	/**
	 * <p>
	 * createBook.
	 * </p>
	 * @param book a {@link Book} object.
	 * @return a {@link reactor.core.publisher.Mono} object.
	 */
	@PostMapping
	public Mono<Book> createBook(@Valid @RequestBody Book book) {
		return this.reactiveBookService.createBook(book);
	}

	/**
	 * <p>
	 * updateBook.
	 * </p>
	 * @param bookId a {@link java.lang.String} object.
	 * @param book a {@link Book} object.
	 * @return a {@link reactor.core.publisher.Mono} object.
	 */
	@PutMapping("/{id}")
	public Mono<ResponseEntity<Book>> updateBook(@PathVariable("id") String bookId, @Valid @RequestBody Book book) {
		return this.reactiveBookService.updateBook(bookId, book);
	}

	/**
	 * <p>
	 * deleteBook.
	 * </p>
	 * @param bookId a {@link java.lang.String} object.
	 * @return a {@link reactor.core.publisher.Mono} object.
	 */
	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> deleteBook(@PathVariable("id") String bookId) {
		return this.reactiveBookService.deleteBook(bookId);
	}

	// Books are Sent to the client as Server Sent Events
	/**
	 * <p>
	 * streamAllBooks.
	 * </p>
	 * @return a {@link reactor.core.publisher.Flux} object.
	 */
	@GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Book> streamAllBooks() {
		return this.reactiveBookService.findAllBooks();
	}

}
