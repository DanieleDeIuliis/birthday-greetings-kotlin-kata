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
        val employeesToGreet = mutableListOf<Employee>()
        while (reader.readLine().also { str = it } != null) {
            val employeeData = str!!.split(", ")
            val employee = Employee(employeeData[1], employeeData[0], employeeData[2], employeeData[3])
            if (employee.isBirthday(xDate)) {
                employeesToGreet.add(employee)
            }
        }

        employeesToGreet.forEach { sendGreetingsTo(it, smtpHost, smtpPort) }
    }

    private fun sendGreetingsTo(
        employee: Employee,
        smtpHost: String,
        smtpPort: Int
    ) {
        val recipient = employee.email
        val body = "Happy Birthday, dear %NAME%".replace("%NAME%", employee.firstName!!)
        val subject = "Happy Birthday!"
        sendMessage(smtpHost, smtpPort, "sender@here.com", subject, body, recipient)
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