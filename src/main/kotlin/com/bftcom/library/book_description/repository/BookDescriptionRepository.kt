package com.bftcom.library.book_description.repository

import com.bftcom.library.book_description.model.BookDescription

interface BookDescriptionRepository {
    fun findBookDescriptionById(id: Long): BookDescription?

    fun createBookDescription(bookDescription: BookDescription): BookDescription

    fun updateBookDescription(bookDescription: BookDescription): BookDescription

    fun findBookDescriptionByIsbn(isbn: String): BookDescription?

    fun deleteBookDescription(id: Long)
}