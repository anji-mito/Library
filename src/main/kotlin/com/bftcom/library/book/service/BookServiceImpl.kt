package com.bftcom.library.book.service

import com.bftcom.library.book.dto.BookDto
import com.bftcom.library.book.model.Book
import com.bftcom.library.book.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class BookServiceImpl(
    private val bookRepository: BookRepository
) : BookService {
    override fun getAll(): List<BookDto> {
        return bookRepository.getAll().map { it.toDto() }
    }

    override fun getById(id: Long): BookDto {
        bookRepository.getById(id)?.let {
            return it.toDto()
        }
        throw IllegalArgumentException("Book with id $id not found")
    }

    override fun create(dto: BookDto): BookDto {
        return bookRepository.create(dto.toModel()).toDto()
    }

    override fun update(id: Long, dto: BookDto): BookDto {
        return bookRepository.update(id, dto.toModel()).toDto()
    }

    override fun delete(id: Long) {
        bookRepository.delete(id)
    }

    private fun Book.toDto() = BookDto(
        id = this.id, title = this.title, author = this.author, status = this.status, isbn = this.isbn
    )

    private fun BookDto.toModel() = Book(
         id = this.id, title = this.title, author = this.author, status = this.status, isbn = this.isbn
    )
}