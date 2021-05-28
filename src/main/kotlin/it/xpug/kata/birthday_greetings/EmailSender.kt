package it.xpug.kata.birthday_greetings

import java.util.*
import javax.mail.Message
import javax.mail.Session

interface SessionBuilder {
    fun createSession(host: String, port: Int): Session?
}

class EmailSessionBuilder: SessionBuilder {
    override fun createSession(host: String, port: Int): Session? {
        val props = Properties()
        props["mail.smtp.host"] = host
        props["mail.smtp.port"] = "" + port
        return Session.getInstance(props, null)
    }
}

class EmailSender(
    private val transporter: Transporter,
    private val messageBuilder: EmailMessageBuilder,
    private val sessionBuilder: SessionBuilder
): MessageSender {



    override fun sendMessage(host: String, port: Int, sender: String, employee: Employee) {
        val session = sessionBuilder.createSession(host, port)

        val msg: Message = messageBuilder.buildMessage(session, sender, employee)

        transporter.send(msg)
    }
}