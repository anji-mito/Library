package com.bftcom.library.book.controller

import com.bftcom.library.book.dto.BookDto
import com.bftcom.library.book.service.BookService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/books")
class BookController(
    private val bookService: BookService,
) {

    @PostMapping()
    fun create(@RequestBody dto: BookDto): BookDto {
        return bookService.create(dto)
    }

    @GetMapping
    fun getAll(): List<BookDto> {
        return bookService.getAll()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Int): BookDto {
        return bookService.getById(id)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody dto: BookDto): BookDto {
        return bookService.update(id, dto)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int) {
        bookService.delete(id)
    }
}