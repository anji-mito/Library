package com.bftcom.library.reader.model

data class Reader(
    var id: Long,
    var name: String,
    var surname: String,
    var email: String,
    var phone: String? = null
)
