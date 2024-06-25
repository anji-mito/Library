package com.bftcom.library.loan.repository

import com.bftcom.library.book.repository.BookRepository
import com.bftcom.library.loan.model.Loan
import com.bftcom.library.reader.repository.ReaderRepository
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import java.sql.ResultSet

@Component
internal class LoanRowMapper(
    private val bookRepository: BookRepository,
    private val readerRepository: ReaderRepository
) : RowMapper<Loan> {
    override fun mapRow(rs: ResultSet, rowNum: Int): Loan {
        return Loan(
            id = rs.getLong("id"),
            book = bookRepository.findById(rs.getLong("book_id"))!!,
            loanDate = rs.getTimestamp("loan_date"),
            returnDate = rs.getTimestamp("return_date"),
            reader = readerRepository.getById(rs.getLong("reader_id"))!!
        )
    }
}