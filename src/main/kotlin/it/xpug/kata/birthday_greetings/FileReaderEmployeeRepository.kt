package it.xpug.kata.birthday_greetings

import java.io.BufferedReader
import java.io.FileReader

class FileReaderEmployeeRepository: EmployeeRepository {

    override fun readEmployee(): List<Employee> {
        var bufferedReader = BufferedReader(FileReader("employee_data.txt"))
        var str: String? = ""
        str = bufferedReader.readLine() // skip header
        var employee = mutableListOf<Employee>();
        while (bufferedReader.readLine().also { str = it } != null) {
            val employeeData = str!!.split(", ")
            employee.add(Employee(employeeData[1], employeeData[0], employeeData[2], employeeData[3]))
        }
        return employee
    }

}