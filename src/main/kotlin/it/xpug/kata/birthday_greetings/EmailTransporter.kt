package it.xpug.kata.birthday_greetings

import javax.mail.Message
import javax.mail.Transport

class EmailTransporter: Transporter {
    override fun send(message: Message) {
        Transport.send(message)
    }
}