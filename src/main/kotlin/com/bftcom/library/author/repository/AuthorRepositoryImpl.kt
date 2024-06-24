package com.bftcom.library.author.repository

import com.bftcom.library.author.model.Author
import com.bftcom.library.book.model.Book
import com.bftcom.library.book.model.Status
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

class AuthorRepositoryImpl(
    private val jdbcTemplate: NamedParameterJdbcTemplate
) : AuthorRepository {
    override fun findAll(): List<Author> {
        return jdbcTemplate.query(
            "select * from authors",
            ROW_MAPPER
        )
    }

    override fun getById(id: Long): Author? {
        return jdbcTemplate.query(
            "select * from authors where id = :id",
            mapOf("id" to id),
            ROW_MAPPER
        ).firstOrNull()
    }

    override fun create(author: Author): Author {
        TODO("Not yet implemented")
    }

    override fun update(id: Long, author: Author): Author {
        TODO("Not yet implemented")
    }

    override fun delete(id: Long) {

    }
    private companion object {
        val ROW_MAPPER = RowMapper<Author> { rs, _ ->
            Author(
                id = rs.getLong("id"),
                name = rs.getString("name"),
                surname = rs.getString("surname"),
                patronymic = rs.getString("patronymic")
            )
        }
    }
}