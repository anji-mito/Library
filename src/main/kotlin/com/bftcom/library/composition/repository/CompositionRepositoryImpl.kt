package com.bftcom.library.composition.repository

import com.bftcom.library.author.model.Author
import com.bftcom.library.composition.model.Composition
import com.bftcom.library.genre.model.Genre
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Repository

@Repository
class CompositionRepositoryImpl(
    private val jdbcTemplate: NamedParameterJdbcTemplate
) : CompositionRepository {

    override fun findAll(): List<Composition> {
        return jdbcTemplate.query(
            "select compositions.*, authors.name as author_name," +
                    " authors.surname as author_surname, authors.patronymic as author_patronymic," +
                    " genres.name as genre_name from compositions" +
                    " join authors on author_id = authors.id" +
                    " join genres on genre_id = genres.id",
            ROW_MAPPER
        )
    }

    override fun findById(id: Long): Composition? {
        return jdbcTemplate.query(
            "select compositions.*, authors.name as author_name," +
                    " authors.surname as author_surname, authors.patronymic as author_patronymic," +
                    " genres.name as genre_name from compositions" +
                    " join authors on author_id = authors.id" +
                    " join genres on genre_id = genres.id" +
                    " where compositions.id = :id",
            mapOf("id" to id),
            ROW_MAPPER
        ).firstOrNull()
    }

    override fun save(composition: Composition): Composition {
        val keyHolder = GeneratedKeyHolder()
        val sqlQuery = "insert into compositions (title, author_id, genre_id) " +
                "values ( :title, :author_id, :genre_id)"
        jdbcTemplate.update(
            sqlQuery,
            MapSqlParameterSource(
                mapOf(
                    "title" to composition.title,
                    "author_id" to composition.author.id,
                    "genre_id" to composition.genre.id
                )
            ),
            keyHolder,
            listOf("id").toTypedArray()
        )
        composition.id = keyHolder.key!!.toLong()
        return composition
    }

    override fun delete(id: Long) {
        jdbcTemplate.update(
            "delete from compositions where id = :id",
            mapOf("id" to id)
        )
    }

    override fun update(composition: Composition): Composition {
        jdbcTemplate.update(
            "update compositions set title = :title, author_id = :author_id, genre_id = :genre_id where id = :id",
            mapOf(
                "id" to composition.id,
                "title" to composition.title,
                "author_id" to composition.author.id,
                "genre_id" to composition.genre.id
            )
        )
        return composition
    }

    private companion object {
        val ROW_MAPPER = RowMapper<Composition> { rs, _ ->
            Composition(
                id = rs.getLong("id"),
                title = rs.getString("title"),
                author = Author(
                    id = rs.getLong("author_id"),
                    name = rs.getString("author_name"),
                    surname = rs.getString("author_surname"),
                    patronymic = rs.getString("author_patronymic")
                ),
                genre = Genre(
                    id = rs.getLong("genre_id"),
                    name = rs.getString("genre_name")
                )
            )
        }
    }
}