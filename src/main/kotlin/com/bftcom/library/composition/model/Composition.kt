package com.bftcom.library.composition.model

import com.bftcom.library.author.model.Author
import com.bftcom.library.genre.model.Genre

data class Composition(
    var id: Long,
    val title: String,
    val author: Author,
    val genre: Genre
)
