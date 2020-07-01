package it.xpug.kata.birthday_greetings.domain

class BirthdayService(
    private val notificationService: NotificationService,
    private val employeeRepository: EmployeeRepository
) {
    fun sendGreetings(date: XDate) {
        employeeRepository.employeesHavingBirthdayAt(date)
            .forEach { notificationService.sendGreetingsTo(it) }
    }

}