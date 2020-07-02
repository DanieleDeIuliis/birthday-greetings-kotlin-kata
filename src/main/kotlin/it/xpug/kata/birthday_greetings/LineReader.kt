package it.xpug.kata.birthday_greetings

interface LineReader {
    fun lines(filename: String): List<String>
}
