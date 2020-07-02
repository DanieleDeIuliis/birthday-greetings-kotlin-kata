package it.xpug.kata.birthday_greetings


fun main() {
    FakeMailServer(port = 3025).start().use {
        val service = BirthdayService("localhost", 3025, FileReaderEmployeeRepository())
        service.sendGreetings(XDate())
    }
}

