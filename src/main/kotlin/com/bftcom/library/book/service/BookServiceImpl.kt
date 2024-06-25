package com.bftcom.library.book.service

import com.bftcom.library.book.dto.BookDto
import com.bftcom.library.book.model.Book
import com.bftcom.library.book.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class BookServiceImpl (
    private val bookRepository: BookRepository
) : BookService {
    override fun getAll(): List<BookDto> {
        TODO()
    }

    override fun getById(id: Long): BookDto {
        TODO()
    }

    override fun create(dto: BookDto): BookDto {
        TODO()
    }

    override fun update(id: Long, dto: BookDto): BookDto {
        TODO()
    }

    override fun delete(id: Long) {
        TODO()
    }
}