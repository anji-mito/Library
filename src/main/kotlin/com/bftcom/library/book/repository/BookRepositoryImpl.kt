package com.bftcom.library.book.repository

import com.bftcom.library.book.model.Book
import com.bftcom.library.book_description.repository.BookDescriptionRepository
import com.bftcom.library.composition.repository.CompositionRepository
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Repository

@Repository
class BookRepositoryImpl(
    private val jdbcTemplate: NamedParameterJdbcTemplate,
    private val bookDescriptionRepository: BookDescriptionRepository,
    private val compositionRepository: CompositionRepository
) : BookRepository {
    override fun create(book: Book): Book {
        val keyHolder = GeneratedKeyHolder()
        val sqlQuery = "insert into books (composition_id, status, book_descriptions_id) " +
                "values ( :compositionId, :status, :bookDescriptionId)"
        jdbcTemplate.update(
            sqlQuery,
            MapSqlParameterSource(
                mapOf(
                    "compositionId" to book.composition.id,
                    "status" to book.status.name,
                    "bookDescriptionId" to book.bookDescription.id
                )
            ),
            keyHolder,
            listOf("id").toTypedArray()
        )
        return Book(
            id = keyHolder.key!!.toLong(),
            composition = book.composition,
            status = book.status,
            bookDescription = book.bookDescription
        )
    }

    override fun findAll(): List<Book> {
        return jdbcTemplate.query(
            "select * from books",
            BookRowMapper(bookDescriptionRepository, compositionRepository)
        )
    }

    override fun findById(id: Long): Book? {
        return jdbcTemplate.query(
            "select * from books where id = :id",
            mapOf("id" to id),
            BookRowMapper(bookDescriptionRepository, compositionRepository)
        ).firstOrNull()
    }

    override fun update(id: Long, book: Book): Book {
        jdbcTemplate.update(
            "update books set composition_id = :compositionId, status = :status, book_descriptions_id = :bookDescriptionId" +
                    " where id = :id",
            mapOf(
                "id" to id,
                "compositionId" to book.composition.id,
                "bookDescriptionId" to book.bookDescription.id,
                "status" to book.status.name,
            )
        )
        return Book(
            id = id,
            composition = book.composition,
            bookDescription = book.bookDescription,
            status = book.status
        )
    }

    override fun delete(id: Long) {
        jdbcTemplate.update(
            "delete from books where id = :id",
            mapOf("id" to id)
        )
    }
}