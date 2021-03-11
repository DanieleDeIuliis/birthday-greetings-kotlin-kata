package it.xpug.kata.birthday_greetings

import javax.mail.Message

interface Transporter {
    fun send(message: Message)
}