package com.bftcom.library.book.repository

import com.bftcom.library.book.model.Book

interface BookRepository {
    fun create(book: Book): Book

    fun findAll(): List<Book>

    fun findById(id: Long): Book?

    fun update(id: Long, book: Book): Book

    fun delete(id: Long)
}