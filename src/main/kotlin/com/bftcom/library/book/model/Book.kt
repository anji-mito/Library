package com.bftcom.library.book.model

data class Book(
    val id: Long,
    val compositionId: Long,
    val status: Status,
    val bookDescriptionId: Long
)
