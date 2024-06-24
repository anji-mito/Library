package com.bftcom.library.book.dto

import com.bftcom.library.book.model.Status

data class BookDto (
    val id: Long = 0,
    val status: Status,
    val bookDescriptionId: Long,
    val compositionId: Long
)