package com.bftcom.library.book.model

import com.bftcom.library.book_description.model.BookDescription
import com.bftcom.library.composition.model.Composition

data class Book(
    val id: Long,
    val composition: Composition,
    val status: Status,
    val bookDescription: BookDescription
)
