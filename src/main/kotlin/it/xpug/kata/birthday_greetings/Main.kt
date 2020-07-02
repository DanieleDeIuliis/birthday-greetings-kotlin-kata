package it.xpug.kata.birthday_greetings


fun main() {
    FakeMailServer(port = 3025).start().use {
        val service = BirthdayService(FileReaderEmployeeRepository())
        service.sendGreetings(XDate(), "localhost", 3025)
    }
}

