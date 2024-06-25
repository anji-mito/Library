package com.bftcom.library.book.repository

import com.bftcom.library.book.model.Book
import com.bftcom.library.book.model.Status
import com.bftcom.library.book_description.repository.BookDescriptionRepository
import com.bftcom.library.composition.repository.CompositionRepository
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import java.sql.ResultSet

@Component
internal class BookRowMapper(
    private val bookDescriptionRepository: BookDescriptionRepository,
    private val compositionRepository: CompositionRepository
) : RowMapper<Book> {
    @Override
    override fun mapRow(rs: ResultSet, rowNum: Int): Book? {
        val compositionId = rs.getLong("composition_id")
        val composition = compositionRepository.findById(compositionId)
        val bookDescriptionId = rs.getLong("book_descriptions_id")
        val bookDescription = bookDescriptionRepository.findBookDescriptionById(bookDescriptionId)
        return Book(
            id = rs.getLong("id"),
            composition = composition!!,
            status = Status.valueOf(rs.getString("status")),
            bookDescription = bookDescription!!
        )
    }
}