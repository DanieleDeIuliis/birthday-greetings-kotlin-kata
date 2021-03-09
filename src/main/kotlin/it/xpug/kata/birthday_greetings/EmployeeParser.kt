package it.xpug.kata.birthday_greetings

class EmployeeParser: InputParser {
    override fun parse(input: String): List<Employee> {
        val employees = mutableListOf<Employee>()
        input.split("\n").forEach { line ->
            if(line.isNotNullOrEmpty()) {
                employees.add(parseEmployee(line))
            }
        }
        return employees
    }

    private fun parseEmployee(str: String?): Employee {
        val employeeData = str!!.split(", ")
        return Employee(employeeData[1], employeeData[0], employeeData[2], employeeData[3])
    }
}