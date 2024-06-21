package com.bftcom.library.book.storage

import com.bftcom.library.book.model.Book
import com.bftcom.library.book.model.Status
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import java.sql.ResultSet
import java.sql.SQLException
import java.util.*


@Component
@Repository
class BookDbStorage @Autowired constructor(final val jdbcTemplate: JdbcTemplate) : BookStorage {
    override fun findFilm(id: Long): Optional<Book> {
        try {
            val sqlQuery =
                ("""SELECT f.ID, f.NAME, f.DESCRIPTION, f.RELEASE_DATE, f.DURATION,                f.ID_RATING, m.id as mpa_id, m.name as mpa_name, COUNT(fl.user_id) as likes,
                GROUP_CONCAT( DISTINCT g.genre_id SEPARATOR ',') as genresid, 
                GROUP_CONCAT(gs.name SEPARATOR ',') as genresnames                FROM FILMS as f
                join mpa as m on m.id = f.id_rating
                LEFT OUTER join films_users_likes as fl on f.id = fl.film_id
                left outer join films_genres as g on g.film_id = f.id
                left outer JOIN genres AS gs ON gs.id = g.GENRE_ID
                where f.id = ? 
                GROUP BY f.id
                ORDER BY COUNT(fl.user_id)""")
            return jdbcTemplate.queryForObject(sqlQuery, this::mapRowToBook, id)!!!!
        } catch (e: EmptyResultDataAccessException) {
            return Optional.empty()
        }
    }

    override fun create(book: Book) {
        TODO("Not yet implemented")
    }

    override fun update(book: Book) {
        TODO("Not yet implemented")
    }

    override fun delete(id: Long) {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<Book> {
        TODO("Not yet implemented")
    }

    override fun findAllByStatus(status: Status): List<Book> {
        TODO("Not yet implemented")
    }

    @Throws(SQLException::class)
    private fun mapRowToBook(resultSet: ResultSet, i: Int): Optional<Book> {
        return Optional.of(
            Book(
                id = resultSet.getLong("ID"),
                name = resultSet.getString("NAME"),
                author = resultSet.getString("AUTHOR"),
                status = Status.valueOf(resultSet.getString("STATUS"))
            )
        )
    }
}