package it.xpug.kata.birthday_greetings

import java.io.BufferedReader
import java.io.FileReader
import java.util.*
import javax.mail.Message
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class BirthdayService(private val notificationService: NotificationService) {
    fun sendGreetings(fileName: String?, xDate: XDate, smtpHost: String, smtpPort: Int) {
        employeesToGreet(fileName, xDate)
            .forEach { notificationService.sendGreetingsTo(it, smtpHost, smtpPort) }
    }

    private fun employeesToGreet(fileName: String?, xDate: XDate): MutableList<Employee> {
        val reader = BufferedReader(FileReader(fileName))
        var str: String? = ""
        str = reader.readLine() // skip header

        val employeesToGreet = mutableListOf<Employee>()
        while (reader.readLine().also { str = it } != null) {
            val employeeData = str!!.split(", ")
            val employee = Employee(employeeData[1], employeeData[0], employeeData[2], employeeData[3])
            if (employee.isBirthday(xDate)) {
                employeesToGreet.add(employee)
            }
        }
        return employeesToGreet
    }
}