package it.xpug.kata.birthday_greetings.domain

interface EmployeeRepository {
    fun employeesHavingBirthdayAt(xDate: XDate): MutableList<Employee>
}