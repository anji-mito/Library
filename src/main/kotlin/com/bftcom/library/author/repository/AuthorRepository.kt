package com.bftcom.library.author.repository

import com.bftcom.library.author.model.Author

interface AuthorRepository {
    fun findAll(): List<Author>

    fun findById(id: Long): Author?

    fun save(author: Author): Author

    fun update(id: Long, author: Author): Author

    fun delete(id: Long)
}