package com.bftcom.library.composition.repository

import com.bftcom.library.composition.model.Composition

interface CompositionRepository {

    fun findAll(): List<Composition>

    fun findById(id: Long): Composition?

    fun save(composition: Composition): Composition

    fun delete(id: Long)

    fun update(composition: Composition): Composition
}