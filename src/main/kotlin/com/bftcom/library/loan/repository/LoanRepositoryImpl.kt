package com.bftcom.library.loan.repository

import com.bftcom.library.loan.model.Loan
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

class LoanRepositoryImpl(
    private val jdbcTemplate: NamedParameterJdbcTemplate): LoanRepository {
    override fun save(loan: Loan): Loan {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<Loan> {
        TODO("Not yet implemented")
    }

    override fun findById(id: Long): Loan {
        TODO()
    }

    override fun delete(loan: Loan) {
        TODO("Not yet implemented")
    }

    override fun update(loan: Loan) {
        TODO("Not yet implemented")
    }
}