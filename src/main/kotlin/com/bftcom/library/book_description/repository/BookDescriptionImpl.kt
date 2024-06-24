package com.bftcom.library.book_description.repository

import com.bftcom.library.book_description.model.BookDescription
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

class BookDescriptionImpl(
    private val jdbcTemplate: NamedParameterJdbcTemplate
): BookDescriptionRepository {
    override fun getBookDescriptionByIsbn(isbn: String): String {
        TODO("Not yet implemented")
    }

    override fun getBookDescriptionById(id: Long): String {
        TODO("Not yet implemented")
    }

    override fun createBookDescription(bookDescription: BookDescription) {
        TODO("Not yet implemented")
    }

    override fun updateBookDescription(bookDescription: BookDescription) {
        TODO("Not yet implemented")
    }

    override fun deleteBookDescription(id: Long) {
        TODO("Not yet implemented")
    }
}