package it.xpug.kata.birthday_greetings.domain

import it.xpug.kata.birthday_greetings.domain.Employee

interface NotificationService {
    fun sendGreetingsTo(employee: Employee)
}
