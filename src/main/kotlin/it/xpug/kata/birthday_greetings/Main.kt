package it.xpug.kata.birthday_greetings

import java.io.BufferedReader
import java.io.FileReader


fun main() {
    FakeMailServer(port = 3025).start().use {
        val service = BirthdayService()
        service.sendGreetings(XDate(), "localhost", 3025, FileReaderEmployeeRepository())
    }
}

