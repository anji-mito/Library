package com.bftcom.library.genre.repository

import com.bftcom.library.genre.model.Genre

interface GenreRepository {

    fun findById(id: Long): Genre?

    fun findAll(): List<Genre>

    fun save(genre: Genre): Genre

    fun delete(genre: Genre)

    fun update(genre: Genre): Genre
}