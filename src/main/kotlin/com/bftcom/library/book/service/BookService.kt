package com.bftcom.library.book.service

import com.bftcom.library.book.dto.BookDto


interface BookService {
    fun getAll(): List<BookDto>
    fun getById(id: Int): BookDto
    fun create(dto: BookDto): BookDto
    fun update(id: Int, dto: BookDto): BookDto
    fun delete( id: Int)
}