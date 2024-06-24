package com.bftcom.library.genre.repository

import com.bftcom.library.genre.model.Genre
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class GenreRepositoryImpl(
    private val jdbcTemplate: NamedParameterJdbcTemplate
) : GenreRepository {
    override fun findById(id: Long): Genre? {
        return jdbcTemplate.query(
            "select * from genres where id = :id",
            mapOf("id" to id),
            ROW_MAPPER
        ).firstOrNull()
    }

    override fun findAll(): List<Genre> {
        return jdbcTemplate.query(
            "select * from genres",
            ROW_MAPPER
        )
    }

    override fun save(genre: Genre): Genre {
        jdbcTemplate.update(
            "insert into genres (name) values (:name)",
            mapOf("name" to genre.name)
        )
        return genre
    }

    override fun delete(genre: Genre) {
        jdbcTemplate.update(
            "delete from genres where id = :id",
            mapOf("id" to genre.id)
        )
    }

    override fun update(genre: Genre): Genre {
        jdbcTemplate.update(
            "update genres set name = :name where id = :id",
            mapOf("name" to genre.name, "id" to genre.id)
        )
        return genre
    }

    private companion object {
        val ROW_MAPPER = RowMapper<Genre> { rs, _ ->
            Genre(
                id = rs.getLong("id"),
                name = rs.getString("name")
            )
        }
    }
}