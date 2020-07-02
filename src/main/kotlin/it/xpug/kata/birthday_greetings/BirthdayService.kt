package it.xpug.kata.birthday_greetings

class BirthdayService(val notificationService: NotificationService, val lineReader: LineReader) {
    fun sendGreetings(fileName: String?, xDate: XDate) {
        val lines = lineReader.lines(fileName!!)
        lines
            .takeLast(lines.size - 1)
            .forEach {
                val employeeData = it.split(", ")
                val employee = Employee(employeeData[1], employeeData[0], employeeData[2], employeeData[3])
                if (employee.isBirthday(xDate)) {
                    val recipient = employee.email
                    val body = "Happy Birthday, dear %NAME%".replace("%NAME%", employee.firstName!!)
                    val subject = "Happy Birthday!"
                    notificationService.sendMessage("sender@here.com", subject, body, recipient)
                }
            }
    }
}