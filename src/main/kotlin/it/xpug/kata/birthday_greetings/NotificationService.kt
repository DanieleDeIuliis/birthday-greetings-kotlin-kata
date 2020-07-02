package it.xpug.kata.birthday_greetings

interface NotificationService {
    fun sendMessage(
        sender: String,
        subject: String,
        body: String,
        recipient: String?
    )
}