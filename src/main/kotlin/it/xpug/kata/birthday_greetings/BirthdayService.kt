package it.xpug.kata.birthday_greetings

import java.io.BufferedReader
import java.io.FileReader

class BirthdayService(val mailNotificationService: MailNotificationService) {
    fun sendGreetings(fileName: String?, xDate: XDate) {
        val reader = BufferedReader(FileReader(fileName))
        var str : String? = ""
        str = reader.readLine() // skip header
        while (reader.readLine().also { str = it } != null) {
            val employeeData = str!!.split(", ")
            val employee = Employee(employeeData[1], employeeData[0], employeeData[2], employeeData[3])
            if (employee.isBirthday(xDate)) {
                val recipient = employee.email
                val body = "Happy Birthday, dear %NAME%".replace("%NAME%", employee.firstName!!)
                val subject = "Happy Birthday!"
                mailNotificationService.sendMessage(
                    "sender@here.com",
                    subject,
                    body,
                    recipient
                )
            }
        }
    }
}