package com.bftcom.library.book.repository

import com.bftcom.library.book.model.Book
import com.bftcom.library.book.model.Status
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Repository

@Repository
class BookRepositoryImpl(
    private val jdbcTemplate: NamedParameterJdbcTemplate
) : BookRepository {
    override fun create(book: Book): Book {
        val keyHolder = GeneratedKeyHolder()
        jdbcTemplate.update(
            "insert into book (id, title, author, status) values (:id, :title, :author, :status)",
            MapSqlParameterSource(
                mapOf(
                    "title" to book.title,
                    "author" to book.author,
                    "status" to book.status
                )),
            keyHolder,
            listOf("id").toTypedArray()
        )
        return Book(
            id = keyHolder.key!!.toLong(),
            title = book.title,
            author = book.author,
            status = book.status
        )
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
        val keyHolder = GeneratedKeyHolder()
        jdbcTemplate.update(
            "insert into book (id, title, author, status) values (:id, :title, :author, :status)",
            MapSqlParameterSource(
            mapOf(
                "id" to id,
                "title" to book.title,
                "author" to book.author,
                "status" to book.status
            )),
            keyHolder,
            listOf("id").toTypedArray()
        )
        return Book(
            id = keyHolder.key!!.toLong(),
            title = book.title,
            author = book.author,
            status = book.status
        )
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