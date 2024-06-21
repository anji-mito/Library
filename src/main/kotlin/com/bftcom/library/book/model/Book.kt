package com.bftcom.library.book.model

data class Book (
    val id: Long,
    val name: String,
    val author: String,
    val status: Status
)
