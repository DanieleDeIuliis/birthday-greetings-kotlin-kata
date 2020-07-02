package it.xpug.kata.birthday_greetings

import java.util.*
import javax.mail.Message
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class BirthdayService {
    fun sendGreetings(
        xDate: XDate,
        smtpHost: String,
        smtpPort: Int,
        employeeRepository: EmployeeRepository
    ) {

        var readEmployee = employeeRepository.readEmployee()

        readEmployee.forEach { employee ->
            if (employee.isBirthday(xDate)) {
                val recipient = employee.email
                val body = "Happy Birthday, dear %NAME%".replace("%NAME%", employee.firstName!!)
                val subject = "Happy Birthday!"
                sendMessage(smtpHost, smtpPort, "sender@here.com", subject, body, recipient)
            }
        }
    }

    private fun sendMessage(
        smtpHost: String,
        smtpPort: Int,
        sender: String,
        subject: String,
        body: String,
        recipient: String?
    ) {
        // Create a mail session
        val props = Properties()
        props["mail.smtp.host"] = smtpHost
        props["mail.smtp.port"] = "" + smtpPort
        val session = Session.getInstance(props, null)

        // Construct the message
        val msg: Message = MimeMessage(session)
        msg.setFrom(InternetAddress(sender))
        msg.setRecipient(Message.RecipientType.TO, InternetAddress(recipient))
        msg.subject = subject
        msg.setText(body)

        // Send the message
        Transport.send(msg)
    }
}