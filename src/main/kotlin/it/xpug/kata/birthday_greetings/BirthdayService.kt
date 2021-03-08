package it.xpug.kata.birthday_greetings

import java.io.BufferedReader
import java.io.FileReader
import java.util.*
import javax.mail.Message
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class BirthdayService {
    fun sendGreetings(fileName: String?, xDate: XDate, smtpHost: String, smtpPort: Int) {
        val reader = BufferedReader(FileReader(fileName))
        var str : String? = ""
        str = reader.readLine() // skip header
        while (reader.readLine().also { str = it } != null) {
            val employee = parseEmployee(str)
            if (employee.isBirthday(xDate)) {
                val recipient = employee.email
                val body = "Happy Birthday, dear ${employee.firstName}!"
                val subject = "Happy Birthday!"
                sendMessage(smtpHost, smtpPort, "sender@here.com", subject, body, recipient)
            }
        }
    }

    private fun parseEmployee(str: String?): Employee {
        val employeeData = str!!.split(", ")
        return Employee(employeeData[1], employeeData[0], employeeData[2], employeeData[3])
    }

    private fun sendMessage(
        smtpHost: String,
        smtpPort: Int,
        sender: String,
        subject: String,
        body: String,
        recipient: String?
    ) {
        val session = createSession(smtpHost, smtpPort)

        val msg: Message = buildMessage(session, sender, recipient, subject, body)

        Transport.send(msg)
    }

    private fun buildMessage(
        session: Session?,
        sender: String,
        recipient: String?,
        subject: String,
        body: String
    ): Message {
        val msg: Message = MimeMessage(session)
        msg.setFrom(InternetAddress(sender))
        msg.setRecipient(Message.RecipientType.TO, InternetAddress(recipient))
        msg.subject = subject
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