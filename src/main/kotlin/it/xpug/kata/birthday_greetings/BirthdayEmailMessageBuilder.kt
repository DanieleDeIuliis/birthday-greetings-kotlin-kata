package it.xpug.kata.birthday_greetings

import javax.mail.Message
import javax.mail.Session
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class BirthdayEmailMessageBuilder: EmailMessageBuilder {

    private val messageSubject: String = "Happy Birthday!"

    override fun buildMessage(
        session: Session?,
        sender: String,
        employee: Employee
    ): Message {
        val recipient = employee.email
        val body = "Happy Birthday, dear ${employee.firstName}!"
        val msg: Message = MimeMessage(session)
        msg.setFrom(InternetAddress(sender))
        msg.setRecipient(Message.RecipientType.TO, InternetAddress(recipient))
        msg.subject = messageSubject
        msg.setText(body)
        return msg
    }
}

