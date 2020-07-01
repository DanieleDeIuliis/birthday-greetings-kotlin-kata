package it.xpug.kata.birthday_greetings

import it.xpug.kata.birthday_greetings.domain.BirthdayService
import it.xpug.kata.birthday_greetings.domain.XDate
import it.xpug.kata.birthday_greetings.infrastructure.FakeMailServer
import it.xpug.kata.birthday_greetings.infrastructure.FileEmployeeRepository
import it.xpug.kata.birthday_greetings.infrastructure.MailNotificationService


fun main() {
    FakeMailServer(port = 3025).start().use {
        val service = BirthdayService(
            MailNotificationService("localhost", 3025),
            FileEmployeeRepository("employee_data.txt")
        )
        service.sendGreetings(XDate())
    }
}

