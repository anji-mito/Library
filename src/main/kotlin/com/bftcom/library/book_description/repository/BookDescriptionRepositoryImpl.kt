package com.bftcom.library.book_description.repository

import com.bftcom.library.book_description.model.BookDescription
import com.bftcom.library.composition.repository.CompositionRepository
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Repository

@Repository
class BookDescriptionRepositoryImpl(
    private val jdbcTemplate: NamedParameterJdbcTemplate,
    private val compositionRepository: CompositionRepository
) : BookDescriptionRepository {
    override fun findBookDescriptionById(id: Long): BookDescription? {
        return jdbcTemplate.query(
            "SELECT * FROM book_descriptions WHERE id = :id",
            mapOf("id" to id),
            BookDescriptionRowMapper(compositionRepository)
        ).firstOrNull()
    }

    override fun createBookDescription(bookDescription: BookDescription): BookDescription {
        val keyHolder = GeneratedKeyHolder()
        jdbcTemplate.update(
            "INSERT INTO book_descriptions (composition_id, description, page_count, isbn)" +
                    " VALUES (:compositionId, :description, :pageCount, :isbn)",
            MapSqlParameterSource(
                mapOf(
                    "compositionId" to bookDescription.composition.id,
                    "description" to bookDescription.description,
                    "pageCount" to bookDescription.pageCount,
                    "isbn" to bookDescription.isbn
                )
            ),
            keyHolder,
            listOf("id").toTypedArray()
        )
        bookDescription.id = keyHolder.key!!.toLong()
        return bookDescription
    }

    override fun updateBookDescription(bookDescription: BookDescription): BookDescription {
        jdbcTemplate.update(
            "UPDATE book_descriptions " +
                    "SET composition_id = :compositionId, description = :description, page_count = :pageCount, isbn = :isbn " +
                    "WHERE id = :id",
            MapSqlParameterSource(
                mapOf(
                    "id" to bookDescription.id,
                    "compositionId" to bookDescription.composition.id,
                    "description" to bookDescription.description,
                    "pageCount" to bookDescription.pageCount,
                    "isbn" to bookDescription.isbn
                )
            )
        )
        return bookDescription
    }

    override fun findBookDescriptionByIsbn(isbn: String): BookDescription? {
        return jdbcTemplate.query(
            "SELECT * FROM book_descriptions WHERE isbn = :isbn",
            mapOf("isbn" to isbn),
            BookDescriptionRowMapper(compositionRepository)
        ).firstOrNull()
    }

    override fun deleteBookDescription(id: Long) {
        jdbcTemplate.update(
            "DELETE FROM book_descriptions WHERE id = :id",
            mapOf("id" to id)
        )
    }
}