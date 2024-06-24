package com.bftcom.library.loan.repository

import com.bftcom.library.loan.model.Loan

interface LoanRepository {

    fun save(loan: Loan): Loan

    fun findAll(): List<Loan>

    fun findById(id: Long): Loan

    fun delete(loan: Loan)

    fun update(loan: Loan)
}