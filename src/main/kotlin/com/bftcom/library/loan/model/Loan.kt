package com.bftcom.library.loan.model

import com.bftcom.library.book.model.Book
import com.bftcom.library.reader.model.Reader
import java.sql.Timestamp

data class Loan(
    var id: Long,
    val reader: Reader,
    val book: Book,
    val loanDate: Timestamp,
    val returnDate: Timestamp
)