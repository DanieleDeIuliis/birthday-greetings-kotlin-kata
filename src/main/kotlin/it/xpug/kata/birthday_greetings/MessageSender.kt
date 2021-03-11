package it.xpug.kata.birthday_greetings

interface MessageSender {
    fun sendMessage( host: String, port: Int, sender: String, employee: Employee)
}