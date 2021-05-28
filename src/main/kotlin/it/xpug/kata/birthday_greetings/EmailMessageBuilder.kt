package it.xpug.kata.birthday_greetings

import javax.mail.Message
import javax.mail.Session

interface EmailMessageBuilder {
    fun buildMessage(session: Session?, sender: String, employee: Employee): Message
}