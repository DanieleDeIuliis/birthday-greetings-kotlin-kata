package it.xpug.kata.birthday_greetings


fun main() {
    FakeMailServer(port = 3025).start().use {
        val service = BirthdayService(MailNotificationService("localhost", 3025), FileLineReader())
        service.sendGreetings("employee_data.txt", XDate())
    }
}

