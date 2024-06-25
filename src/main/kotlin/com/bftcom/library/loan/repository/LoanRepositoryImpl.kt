package com.bftcom.library.loan.repository

import com.bftcom.library.book.repository.BookRepository
import com.bftcom.library.loan.model.Loan
import com.bftcom.library.reader.repository.ReaderRepository
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Repository

@Repository
class LoanRepositoryImpl(
    private val jdbcTemplate: NamedParameterJdbcTemplate,
    private val readerRepository: ReaderRepository,
    private val bookRepository: BookRepository
) : LoanRepository {

    override fun save(loan: Loan): Loan {
        val keyHolder = GeneratedKeyHolder()
        jdbcTemplate.update(
            "INSERT INTO loans (book_id, reader_id, loan_date, return_date) " +
                    "VALUES (:bookId, :readerId, :loanDate, :returnDate)",
            MapSqlParameterSource(
                mapOf(
                    "bookId" to loan.book.id,
                    "readerId" to loan.reader.id,
                    "loanDate" to loan.loanDate,
                    "returnDate" to loan.returnDate
                )
            ),
            keyHolder,
            listOf("id").toTypedArray()
        )
        loan.id = keyHolder.key!!.toLong()
        return loan
    }

    override fun findAll(): List<Loan> {
        return jdbcTemplate.query(
            "SELECT * FROM loans",
            LoanRowMapper(bookRepository, readerRepository)
        )
    }

    override fun findById(id: Long): Loan? {
        return jdbcTemplate.query(
            "SELECT * FROM loans WHERE id = :id",
            mapOf("id" to id),
            LoanRowMapper(bookRepository, readerRepository)
        ).firstOrNull()
    }

    override fun delete(loan: Loan) {
        jdbcTemplate.update(
            "DELETE FROM loans WHERE id = :id",
            mapOf("id" to loan.id)
        )
    }

    override fun update(loan: Loan): Loan {
        jdbcTemplate.update(
            "UPDATE loans " +
                    "SET book_id = :bookId, reader_id = :readerId, loan_date = :loanDate, return_date = :returnDate " +
                    "WHERE id = :id",
            MapSqlParameterSource(
                mapOf(
                    "id" to loan.id,
                    "bookId" to loan.book.id,
                    "readerId" to loan.reader.id,
                    "loanDate" to loan.loanDate,
                    "returnDate" to loan.returnDate
                )
            )
        )
        return loan
    }
}