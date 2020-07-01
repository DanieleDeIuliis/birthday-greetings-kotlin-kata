package it.xpug.kata.birthday_greetings

import it.xpug.kata.birthday_greetings.infrastructure.FakeMailServer
import it.xpug.kata.birthday_greetings.infrastructure.MailNotificationService


fun main() {
    FakeMailServer(port = 3025).start().use {
        val service = BirthdayService(MailNotificationService("localhost", 3025))
        service.sendGreetings("employee_data.txt", XDate())
    }
}

