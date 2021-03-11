package it.xpug.kata.birthday_greetings

import java.util.*
import javax.mail.Message
import javax.mail.Session
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class EmailSender(private val transporter: Transporter): MessageSender {

    private val messageSubject: String = "Happy Birthday!"

    override fun sendMessage(host: String, port: Int, sender: String, employee: Employee) {
        val session = createSession(host, port)

        val msg: Message = buildMessage(session, sender, employee)

        transporter.send(msg)
    }

    private fun buildMessage(
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

    private fun createSession(smtpHost: String, smtpPort: Int): Session? {
        val props = Properties()
        props["mail.smtp.host"] = smtpHost
        props["mail.smtp.port"] = "" + smtpPort
        val session = Session.getInstance(props, null)
        return session
    }
}