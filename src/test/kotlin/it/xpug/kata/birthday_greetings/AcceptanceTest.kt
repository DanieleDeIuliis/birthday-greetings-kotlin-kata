package it.xpug.kata.birthday_greetings

import com.dumbster.smtp.SimpleSmtpServer
import com.dumbster.smtp.SmtpMessage
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class AcceptanceTest {
    private var birthdayService: BirthdayService? = null
    private var mailServer: SimpleSmtpServer? = null

    @Before
    fun setUp() {
        mailServer = SimpleSmtpServer.start(NONSTANDARD_PORT)
        birthdayService = BirthdayService()
    }

    @After
    fun tearDown() {
        mailServer!!.stop()
        Thread.sleep(200)
    }

    @Test
    fun willSendGreetings_whenItsSomebodysBirthday() {
        birthdayService!!.sendGreetings(
            "employee_data.txt",
            XDate("2008/10/08"),
            "localhost",
            NONSTANDARD_PORT
        )
        assertEquals("message not sent?", 1, mailServer!!.receivedEmailSize.toLong())
        val message = mailServer!!.receivedEmail.next() as SmtpMessage
        assertEquals("Happy Birthday, dear John!", message.body)
        assertEquals("Happy Birthday!", message.getHeaderValue("Subject"))
        val recipients = message.getHeaderValues("To")
        assertEquals(1, recipients.size.toLong())
        assertEquals("john.doe@foobar.com", recipients[0].toString())
    }

    @Test
    fun willNotSendEmailsWhenNobodysBirthday() {
        birthdayService!!.sendGreetings(
            "employee_data.txt",
            XDate("2008/01/01"),
            "localhost",
            NONSTANDARD_PORT
        )
        assertEquals("what? messages?", 0, mailServer!!.receivedEmailSize.toLong())
    }

    companion object {
        private const val NONSTANDARD_PORT = 9999
    }
}