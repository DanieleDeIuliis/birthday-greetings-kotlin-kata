package it.xpug.kata.birthday_greetings

import com.dumbster.smtp.SimpleSmtpServer
import com.dumbster.smtp.SmtpMessage
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class AcceptanceTest {
    private val inputParser = EmployeeParser()
    private val emailTransporter = EmailTransporter()
    private val emailSender = EmailSender(emailTransporter, BirthdayEmailMessageBuilder(), EmailSessionBuilder())
    private val birthdayService = BirthdayService(inputParser, emailSender)

    private lateinit var mailServer: SimpleSmtpServer

    @Before
    fun setUp() {
        mailServer = SimpleSmtpServer.start(NONSTANDARD_PORT)
    }

    @After
    fun tearDown() {
        mailServer.stop()
        Thread.sleep(200)
    }

    @Test
    fun `will send greetings when it's somebody's birthday`() {
        val inputAsString = """
            |Doe, John, 1982/10/08, john.doe@foobar.com
            |Ann, Mary, 1975/03/11, mary.ann@foobar.com
        |""".trimMargin()
        birthdayService.sendGreetings(inputAsString, XDate("2008/10/08"), "localhost", NONSTANDARD_PORT)
        assertEquals("message not sent?", 1, mailServer.receivedEmailSize.toLong())

        val message = mailServer.receivedEmail.next() as SmtpMessage
        assertEquals("Happy Birthday, dear John!", message.body)
        assertEquals("Happy Birthday!", message.getHeaderValue("Subject"))

        val recipients = message.getHeaderValues("To")
        assertEquals(1, recipients.size.toLong())
        assertEquals("john.doe@foobar.com", recipients[0].toString())
    }

    @Test
    fun `will not send emails when it's nobody's birthday`() {
        val inputAsString = """
            |Doe, John, 1982/10/08, john.doe@foobar.com
            |Ann, Mary, 1975/03/11, mary.ann@foobar.com
        |""".trimMargin()
        birthdayService.sendGreetings(
            inputAsString,
            XDate("2008/01/01"),
            "localhost",
            NONSTANDARD_PORT
        )
        assertEquals("what? messages?", 0, mailServer.receivedEmailSize.toLong())
    }

    companion object {
        private const val NONSTANDARD_PORT = 9999
    }
}