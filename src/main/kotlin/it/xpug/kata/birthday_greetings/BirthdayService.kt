package it.xpug.kata.birthday_greetings

class BirthdayService(private val inputParser: InputParser, private val messageSender: MessageSender) {

    fun sendGreetings(inputAsString: String, xDate: XDate, smtpHost: String, smtpPort: Int) {
        val employees = inputParser.parse(inputAsString)
        employees.forEach { employee ->
            if (employee.isBirthday(xDate)) {
                messageSender.sendMessage(smtpHost, smtpPort, "sender@here.com", employee)
            }
        }
    }
}

