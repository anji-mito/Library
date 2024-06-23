package com.bftcom.library.book.repository

import com.bftcom.library.book.model.Book

interface BookRepository {

    fun create(book: Book): Book
    fun getAll(): List<Book>
    fun getById(id: Long): Book?
    fun update(id: Long, book: Book): Book
    fun delete(id: Long)

}