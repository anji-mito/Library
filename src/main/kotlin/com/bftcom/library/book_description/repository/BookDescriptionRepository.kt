package com.bftcom.library.book_description.repository

import com.bftcom.library.book_description.model.BookDescription

interface BookDescriptionRepository {

    fun getBookDescriptionByIsbn(isbn: String): String

    fun getBookDescriptionById(id: Long): String

    fun createBookDescription(bookDescription: BookDescription)

    fun updateBookDescription(bookDescription: BookDescription)

    fun deleteBookDescription(id: Long)
}