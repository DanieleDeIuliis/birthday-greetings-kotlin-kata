package it.xpug.kata.birthday_greetings

import java.io.BufferedReader
import java.io.FileReader


fun main() {
    FakeMailServer(port = 3025).start().use {
        val inputAsString = createInputString("employee_data.txt")
        val service = BirthdayService(EmployeeParser(), EmailSender(EmailTransporter(), BirthdayEmailMessageBuilder(), EmailSessionBuilder()))
        service.sendGreetings(inputAsString, XDate(), "localhost", 3025)
    }

}

private fun createInputString(fileName: String?): String {
    val reader = BufferedReader(FileReader(fileName))
    reader.readLine() // skip header
    val stringBuilder = StringBuilder()
    reader.lines().forEach { line ->
        if(line.isNotNullOrEmpty()) {
            stringBuilder.append("$line\n")
        }
    }
    return stringBuilder.toString()
}

