package com.bftcom.library.reader.repository

import com.bftcom.library.reader.model.Reader

interface ReaderRepository {
    fun getById(id: Long): Reader?
    fun findAll(): List<Reader>
    fun create(reader: Reader): Reader
    fun update(id: Long, reader: Reader): Reader
    fun delete(id: Long)
}