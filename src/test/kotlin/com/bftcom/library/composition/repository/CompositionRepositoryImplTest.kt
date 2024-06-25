package com.bftcom.library.composition.repository

import com.bftcom.library.author.model.Author
import com.bftcom.library.author.repository.AuthorRepositoryImpl
import com.bftcom.library.composition.model.Composition
import com.bftcom.library.genre.model.Genre
import com.bftcom.library.genre.repository.GenreRepositoryImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CompositionRepositoryImplTest {

    @Autowired
    lateinit var repository: CompositionRepositoryImpl

    @Autowired
    lateinit var authorRepository: AuthorRepositoryImpl

    @Autowired
    lateinit var genreRepository: GenreRepositoryImpl

    @Test
    fun testGetCompositionById() {
        authorRepository.save(Author(1, "Author 1", "Author 1", "Author 1"))
        genreRepository.save(Genre(1, "Genre 1"))
        val composition = Composition(1, "Composition 1",
            Author(1, "Author 1", "Author 1", "Author 1"),
            Genre(1, "Genre 1"))
        repository.save(composition)
        val foundComposition = repository.findById(1)
        if (foundComposition != null) {
            assertEquals(1, foundComposition.id)
            assertEquals("Composition 1", foundComposition.title)
            assertEquals(foundComposition.author, Author(1, "Author 1", "Author 1", "Author 1"))
            assertEquals(foundComposition.genre, Genre(1, "Genre 1"))
        }
    }

}