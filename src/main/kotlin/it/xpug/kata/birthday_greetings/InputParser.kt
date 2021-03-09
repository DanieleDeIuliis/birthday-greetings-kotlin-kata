package it.xpug.kata.birthday_greetings

interface InputParser {
    fun parse(input: String): List<Employee>
}