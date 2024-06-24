package com.bftcom.library.author.repository

import com.bftcom.library.author.model.Author
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Repository

@Repository
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
        val keyHolder = GeneratedKeyHolder()
        val sqlQuery = "insert into authors (name, surname, patronymic) " +
                "values ( :name, :surname, :patronymic)"
        jdbcTemplate.update(
            sqlQuery,
            MapSqlParameterSource(
                mapOf(
                    "name" to author.name,
                    "surname" to author.surname,
                    "patronymic" to author.patronymic
                )
            ),
            keyHolder,
            listOf("id").toTypedArray()
        )
        return Author(
            id = keyHolder.key!!.toLong(),
            name = author.name,
            surname = author.surname,
            patronymic = author.patronymic
        )
    }

    override fun update(id: Long, author: Author): Author {
        jdbcTemplate.update(
            "update authors set name = :name, surname = :surname, patronymic = :patronymic" +
                    " where id = :id",
            mapOf(
                "id" to id,
                "name" to author.name,
                "surname" to author.surname,
                "patronymic" to author.patronymic
            )
        )
        return Author(
            id = id,
            name = author.name,
            surname = author.surname,
            patronymic = author.patronymic
        )
    }

    override fun delete(id: Long) {
        jdbcTemplate
            .update(
                "delete from authors where id = :id",
                mapOf("id" to id)
            )
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