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
        val sqlQuery = "insert into books (composition_id, status, book_descriptions_id) " +
                "values ( :compositionId, :status, :bookDescriptionId)"
        jdbcTemplate.update(
            sqlQuery,
            MapSqlParameterSource(
                mapOf(
                    "compositionId" to book.compositionId,
                    "status" to book.status.name,
                    "bookDescriptionId" to book.bookDescriptionId
                )
            ),
            keyHolder,
            listOf("id").toTypedArray()
        )
        return Book(
            id = keyHolder.key!!.toLong(),
            compositionId = book.compositionId,
            status = book.status,
            bookDescriptionId = book.bookDescriptionId
        )
    }

    override fun getAll(): List<Book> {
        return jdbcTemplate.query(
            "select * from books",
            ROW_MAPPER
        )
    }

    override fun getById(id: Long): Book? {
        return jdbcTemplate.query(
            "select * from books where id = :id",
            mapOf("id" to id),
            ROW_MAPPER
        ).firstOrNull()
    }

    override fun update(id: Long, book: Book): Book {
        jdbcTemplate.update(
            "update books set composition_id = :compositionId, status = :status, book_descriptions_id = :bookDescriptionId" +
                    " where id = :id",
            mapOf(
                "id" to id,
                "compositionId" to book.compositionId,
                "bookDescriptionId" to book.bookDescriptionId,
                "status" to book.status.name,
            )
        )
        return Book(
            id = id,
            bookDescriptionId = book.bookDescriptionId,
            compositionId = book.compositionId,
            status = book.status
        )
    }

    override fun delete(id: Long) {
        jdbcTemplate.update(
            "delete from books where id = :id",
            mapOf("id" to id)
        )
    }

    private companion object {
        val ROW_MAPPER = RowMapper<Book> { rs, _ ->
            Book(
                id = rs.getLong("id"),
                compositionId = rs.getString("composition_id").toLong(),
                bookDescriptionId = rs.getString("book_description_id").toLong(),
                status = Status.valueOf(rs.getString("status"))
            )
        }
    }
}