package com.bftcom.library.book.controller

import com.bftcom.library.book.dto.BookDto
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/books")
class BookController {

    @GetMapping
    fun getAll(): List<BookDto> {
        return listOf(
            BookDto(
                id = 1,
                title = "Book 1",
                author = "Author 1"
            )
        )
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Int): BookDto {
        return BookDto(
            id = id,
            title = "Book $id",
            author = "Author $id"
        )
    }

    @PostMapping()
    fun create(@PathVariable id: Int): BookDto {
        return BookDto(
            id = id,
            title = "Book $id",
            author = "Author $id"
        )
    }
    @PutMapping("/{id}")
    fun update(@PathVariable id: Int): BookDto {
        return BookDto(
            id = id,
            title = "Book $id",
            author = "Author $id"
        )
    }
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int) {

    }
}