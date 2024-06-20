package com.bftcom.library.reader.model

import com.bftcom.library.book.model.Book
import lombok.Data
import java.time.LocalDate

@Data
class Reader {
    var id: String? = null
    var name: String? = null
    var surname: String? = null
    var email: String? = null
    var phone: String? = null
    var registrationDate: LocalDate? = null
    var borrowedBooks: List<Book>? = null
}