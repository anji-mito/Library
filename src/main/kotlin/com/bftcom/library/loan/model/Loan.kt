package com.bftcom.library.loan.model

import com.bftcom.library.book.model.Book
import com.bftcom.library.reader.model.Reader
import java.time.LocalDateTime

data class Loan(
    val id: Long,
    val reader: Reader,
    val book: Book?,
    val loanDate: LocalDateTime,
    val loanDays: Int
)