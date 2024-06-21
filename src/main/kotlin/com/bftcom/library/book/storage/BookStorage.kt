package com.bftcom.library.book.storage

import com.bftcom.library.book.model.Book
import com.bftcom.library.book.model.Status
import java.util.*

interface BookStorage {
    fun findFilm(id: Long): Optional<Book>
    fun create(book: Book)
    fun update(book: Book)
    fun delete(id: Long)
    fun findAll(): List<Book>
    fun findAllByStatus(status: Status): List<Book>
}