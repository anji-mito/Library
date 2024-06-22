package com.bftcom.library.book.repository

import com.bftcom.library.book.model.Book
import com.bftcom.library.book.model.Status
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class BookRepositoryImpl(
    private val jdbcTemplate: NamedParameterJdbcTemplate
) : BookRepository {
    override fun create(book: Book): Book {
        TODO("Not yet implemented")
    }

    override fun getAll(): List<Book> {
        return jdbcTemplate.query(
            "select * from book",
            ROW_MAPPER
        )
    }

    override fun getById(id: Int): Book? {
        return jdbcTemplate.query(
            "select * from book where id = :id",
            mapOf("id" to id),
            ROW_MAPPER
        ).firstOrNull()
    }

    override fun update(id: Int, book: Book): Book {
        TODO("Not yet implemented")
    }

    override fun delete(id: Int) {
        TODO("Not yet implemented")
    }

    private companion object {
        val ROW_MAPPER = RowMapper<Book> { rs, _ ->
            Book(
                id = rs.getLong("id"),
                title = rs.getString("title"),
                author = rs.getString("author"),
                status = Status.valueOf(rs.getString("status"))
            )
        }
    }
}