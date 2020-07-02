package it.xpug.kata.birthday_greetings

import java.io.BufferedReader
import java.io.FileReader

class FileLineReader : LineReader {
    override fun lines(filename: String): List<String> {
        return BufferedReader(FileReader(filename)).readLines()
    }
}