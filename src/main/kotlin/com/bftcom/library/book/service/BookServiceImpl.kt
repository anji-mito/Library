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
        return bookRepository.getAll()
            .map { it.toDto() }
    }

    override fun getById(id: Int): BookDto {
        bookRepository.getById(id)?.let {
            return it.toDto()
        }
        throw IllegalArgumentException("Book with id $id not found")
    }

    override fun create(dto: BookDto): BookDto {
        return bookRepository.create(dto.toModel()).toDto()
    }

    override fun update(id: Int, dto: BookDto): BookDto {
        TODO("Not yet implemented")
    }

    override fun delete(id: Int) {
        TODO("Not yet implemented")
    }

    private fun Book.toDto() = BookDto(
        id = this.id,
        title = this.title,
        author = this.author,
        status = this.status
    )
    private fun BookDto.toModel() = Book(
        id = this.id,
        title = this.title,
        author = this.author,
        status = this.status
    )
}