package com.bftcom.library.book.repository

import com.bftcom.library.book.model.Book

interface BookRepository {

    fun create(book: Book): Book
    fun getAll(): List<Book>
    fun getById(id: Int): Book?
    fun update(id: Int, book: Book): Book
    fun delete(id: Int)

}