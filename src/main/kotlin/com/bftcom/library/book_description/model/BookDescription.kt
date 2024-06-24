package com.bftcom.library.book_description.model

data class BookDescription(
    val id: Long,
    val description: String,
    val isbn: String,
    val pageCount: Int
)