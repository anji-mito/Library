package com.bftcom.library.book.dto

import com.bftcom.library.book.model.Status

data class BookDto (
    val id: Long = 0,
    val title: String,
    val author: String? = null,
    val status: Status
)