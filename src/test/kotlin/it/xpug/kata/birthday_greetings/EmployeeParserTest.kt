package it.xpug.kata.birthday_greetings

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class EmployeeParserTest {
    @Test
    fun `when a well formatted employee line is passed, then the employee is properly created`() {
        val line = "Doe, John, 1982/10/08, john.doe@foobar.com"
        val expectedEmployee = Employee("John", "Doe", "1982/10/08", "john.doe@foobar.com")

        val employeeParser = EmployeeParser()
        val employees = employeeParser.parse(line)

        assertEquals(false, employees.isEmpty())
        assertEquals(expectedEmployee, employees.first())
    }
}