package com.example.bouncycastle

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/file")
class Endpoints(val fileReader: FileReader) {

    @GetMapping
    fun readFile() {
        fileReader.read("YOUR_FILE_PATH", "YOUR_FILE_PASSWORD")
    }
}