package it.xpug.kata.birthday_greetings


fun main() {
    FakeMailServer(port = 3025).start().use {
        val service = BirthdayService(MailNotificationService())
        service.sendGreetings("employee_data.txt", XDate(), "localhost", 3025)
    }
}

