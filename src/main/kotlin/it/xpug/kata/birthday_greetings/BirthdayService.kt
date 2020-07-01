package it.xpug.kata.birthday_greetings

import java.io.BufferedReader
import java.io.FileReader

class BirthdayService(private val notificationService: NotificationService) {
    fun sendGreetings(fileName: String?, xDate: XDate) {
        employeesToGreet(fileName, xDate)
            .forEach { notificationService.sendGreetingsTo(it) }
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