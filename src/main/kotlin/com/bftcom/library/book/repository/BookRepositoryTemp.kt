package com.bftcom.library.book.repository

import com.bftcom.library.book.model.Book
import org.springframework.stereotype.Repository

@Repository // TODO: remove
class BookRepositoryTemp: BookRepository {
    override fun create(book: Book): Book {
        TODO("Not yet implemented")
    }

    override fun getAll(): List<Book> {
        TODO("Not yet implemented")
    }

    override fun getById(id: Long): Book? {
        TODO("Not yet implemented")
    }

    override fun update(id: Long, book: Book): Book {
        TODO("Not yet implemented")
    }

    override fun delete(id: Long) {
        TODO("Not yet implemented")
    }
}