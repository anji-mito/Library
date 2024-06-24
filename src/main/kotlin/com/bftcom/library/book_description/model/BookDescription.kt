package com.bftcom.library.book_description.model

import com.bftcom.library.composition.model.Composition

data class BookDescription(
    val id: Long,
    val composition: Composition,
    val description: String,
    val isbn: String,
    val pageCount: Int
)