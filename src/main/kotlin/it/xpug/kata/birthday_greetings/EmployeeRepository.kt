package it.xpug.kata.birthday_greetings

interface EmployeeRepository {

    fun readEmployee(): List<Employee>
}