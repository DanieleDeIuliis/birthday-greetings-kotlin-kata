package it.xpug.kata.birthday_greetings.domain

interface NotificationService {
    fun sendGreetingsTo(employee: Employee)
}
