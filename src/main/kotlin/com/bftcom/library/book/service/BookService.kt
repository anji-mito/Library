package com.bftcom.library.book.service

import com.bftcom.library.book.dto.BookDto


interface BookService {
    fun getAll(): List<BookDto>
    fun getById(id: Long): BookDto
    fun create(dto: BookDto): BookDto
    fun update(id: Long, dto: BookDto): BookDto
    fun delete( id: Long)
}