package com.bftcom.library.loan.model

import com.bftcom.library.book.model.Book
import com.bftcom.library.reader.model.Reader
import java.time.LocalDate
import java.time.LocalDateTime

class Loan {
    var reader: Reader = Reader()
    var book: Book = Book()
    var loanDate: LocalDateTime? = LocalDateTime.now()
    var returnDate: LocalDate? = null
}