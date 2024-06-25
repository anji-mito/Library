package com.bftcom.library.author.model

data class Author(
    var id: Long,
    val name: String,
    val surname: String,
    val patronymic: String?
)
