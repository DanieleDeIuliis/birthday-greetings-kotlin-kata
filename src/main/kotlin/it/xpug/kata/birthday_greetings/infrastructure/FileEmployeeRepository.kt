package it.xpug.kata.birthday_greetings.infrastructure

import it.xpug.kata.birthday_greetings.domain.Employee
import it.xpug.kata.birthday_greetings.domain.EmployeeRepository
import it.xpug.kata.birthday_greetings.domain.XDate
import java.io.BufferedReader
import java.io.FileReader

class FileEmployeeRepository(private val fileName: String?) : EmployeeRepository {
    override fun employeesToGreet(xDate: XDate): MutableList<Employee> {
        val reader = BufferedReader(FileReader(fileName))
        var str: String? = ""
        str = reader.readLine() // skip header

        val employeesToGreet = mutableListOf<Employee>()
        while (reader.readLine().also { str = it } != null) {
            val employeeData = str!!.split(", ")
            val employee = Employee(
                employeeData[1],
                employeeData[0],
                employeeData[2],
                employeeData[3]
            )
            if (employee.isBirthday(xDate)) {
                employeesToGreet.add(employee)
            }
        }
        return employeesToGreet
    }

}
