package com.bftcom.library.book.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/books")
class BookController {
    @GetMapping
    fun hello(): String {
        return "Hello World"
    }
}