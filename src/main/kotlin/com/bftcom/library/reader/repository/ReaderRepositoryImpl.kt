package com.bftcom.library.reader.repository

import com.bftcom.library.reader.model.Reader
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Repository

@Repository
class ReaderRepositoryImpl(
    private val jdbcTemplate: NamedParameterJdbcTemplate
) : ReaderRepository {
    override fun getById(id: Long): Reader? {
        return jdbcTemplate.query(
            "select * from readers where id = :id",
            mapOf("id" to id),
            ROW_MAPPER
        ).firstOrNull()
    }

    override fun findAll(): List<Reader> {
        return jdbcTemplate.query(
            "select * from readers",
            ROW_MAPPER
        )
    }

    override fun create(reader: Reader): Reader {
        val keyHolder = GeneratedKeyHolder()
        val sqlQuery = "insert into readers (name, surname, email, phone) " +
                "values ( :name, :surname, :email, phone)"
        jdbcTemplate.update(
            sqlQuery,
            MapSqlParameterSource(
                mapOf(
                    "name" to reader.name,
                    "surname" to reader.surname,
                    "email" to reader.email,
                    "phone" to reader.phone
                )
            ),
            keyHolder,
            listOf("id").toTypedArray()
        )
        return Reader(
            id = keyHolder.key!!.toLong(),
            name = reader.name,
            surname = reader.surname,
            phone = reader.phone,
            email = reader.email
        )
    }

    override fun update(id: Long, reader: Reader): Reader {
        jdbcTemplate.update(
            "update readers set name = :name, surname = :surname, email = :email, phone = :phone" +
                    " where id = :id",
            mapOf(
                "id" to id,
                "name" to reader.name,
                "surname" to reader.surname,
                "email" to reader.email,
                "phone" to reader.phone
            )
        )
        return reader
    }

    override fun delete(id: Long) {
        jdbcTemplate.update(
            "delete from readers where id = :id",
            mapOf("id" to id)
        )
    }

    private companion object {
        val ROW_MAPPER = RowMapper<Reader> { rs, _ ->
            Reader(
                id = rs.getLong("id"),
                name = rs.getString("name"),
                surname = rs.getString("surname"),
                email = rs.getString("email"),
                phone = rs.getString("phone")
            )
        }
    }
}