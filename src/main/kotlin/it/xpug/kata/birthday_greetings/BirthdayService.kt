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

    private val messageSubject: String = "Happy Birthday!"

    fun sendGreetings(fileName: String?, xDate: XDate, smtpHost: String, smtpPort: Int) {
        val employees = parseInput(fileName)
        employees.forEach { employee ->
            if (employee.isBirthday(xDate)) {
                sendMessage(smtpHost, smtpPort, "sender@here.com", employee)
            }
        }
    }

    private fun parseInput(fileName: String?): MutableList<Employee> {
        val reader = BufferedReader(FileReader(fileName))
        var str: String? = ""
        str = reader.readLine() // skip header
        val employees = mutableListOf<Employee>()
        while (reader.readLine().also { str = it } != null) {
            employees.add(parseEmployee(str))
        }
        return employees
    }

    private fun parseEmployee(str: String?): Employee {
        val employeeData = str!!.split(", ")
        return Employee(employeeData[1], employeeData[0], employeeData[2], employeeData[3])
    }

    private fun sendMessage(
        smtpHost: String,
        smtpPort: Int,
        sender: String,
        employee: Employee
    ) {
        val session = createSession(smtpHost, smtpPort)

        val msg: Message = buildMessage(session, sender, employee)

        Transport.send(msg)
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