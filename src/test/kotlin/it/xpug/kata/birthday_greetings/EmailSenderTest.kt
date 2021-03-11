package it.xpug.kata.birthday_greetings

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class EmailSenderTest {

    private val transporter: Transporter = mockk(relaxed = true)

    @Test
    fun `build and sends a message`() {
        val emailSender = EmailSender(transporter)
        emailSender.sendMessage("host", 8080, "sender", Employee("firstName", "lastName","2021/01/01","a.b@c"))

        verify { transporter.send(any()) }
    }
}