package com.bftcom.library.book_description.repository

import com.bftcom.library.book_description.model.BookDescription
import com.bftcom.library.composition.repository.CompositionRepository
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

internal class BookDescriptionRowMapper(private val compositionRepository: CompositionRepository) :
    RowMapper<BookDescription> {
    override fun mapRow(rs: ResultSet, rowNum: Int): BookDescription {
        val compositionId = rs.getLong("composition_id")
        val composition = compositionRepository.findById(compositionId)
        return BookDescription(
            id = rs.getLong("id"),
            composition = composition!!,
            description = rs.getString("description"),
            pageCount = rs.getInt("page_count"),
            isbn = rs.getString("isbn"),
        )
    }
}