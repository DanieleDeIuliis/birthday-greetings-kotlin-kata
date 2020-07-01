package it.xpug.kata.birthday_greetings.domain

interface EmployeeRepository {
    fun employeesToGreet(xDate: XDate): MutableList<Employee>
}