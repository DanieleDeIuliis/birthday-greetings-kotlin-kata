package it.xpug.kata.birthday_greetings

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.Test
import java.util.*
import javax.mail.Message
import javax.mail.Session
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class EmailSenderTest {

    private val transporter: Transporter = mock()
    private val emailMessageBuilder: EmailMessageBuilder = mock()
    private val sessionBuilder = mock<SessionBuilder>()

    @Test
    fun `build and sends a message`() {
        val employee = Employee("firstName", "lastName", "2021/01/01", "a.b@c")
        val session = Session.getInstance(Properties(), null)
        val message: Message = aMessage("sender", "a.b@c", "Happy Birthday!", "Happy Birthday, dear firstName!", session)
        whenever(sessionBuilder.createSession("host", 8080)).thenReturn(session)
        whenever(emailMessageBuilder.buildMessage(session,"sender", employee)).thenReturn(message)

        val emailSender = EmailSender(transporter, emailMessageBuilder, sessionBuilder)
        emailSender.sendMessage("host", 8080, "sender", employee)

        //verify(transporter).send(message)
    }

    private fun aMessage(sender: String, email: String, subject: String, body: String, session: Session): Message {
        val message: Message = MimeMessage(session)
        message.setFrom(InternetAddress(sender))
        message.setRecipient(Message.RecipientType.TO, InternetAddress(email))
        message.subject = subject
        message.setText(body)
        return message
    }
}