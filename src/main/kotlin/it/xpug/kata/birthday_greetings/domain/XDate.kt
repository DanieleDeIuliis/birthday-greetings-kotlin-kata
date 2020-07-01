package it.xpug.kata.birthday_greetings.domain

import java.text.SimpleDateFormat
import java.util.*

class XDate {
    private var date: Date

    constructor() {
        date = Date()
    }

    constructor(yyyyMMdd: String?) {
        date = SimpleDateFormat("yyyy/MM/dd").parse(yyyyMMdd)
    }

    val day: Int
        get() = getPartOfDate(GregorianCalendar.DAY_OF_MONTH)

    val month: Int
        get() = 1 + getPartOfDate(GregorianCalendar.MONTH)

    fun isSameDay(anotherDate: XDate?): Boolean {
        return anotherDate!!.day == day && anotherDate.month == month
    }

    override fun hashCode(): Int {
        return date.hashCode()
    }

    override fun equals(obj: Any?): Boolean {
        if (obj !is XDate) return false
        return obj.date == date
    }

    private fun getPartOfDate(part: Int): Int {
        val calendar = GregorianCalendar()
        calendar.time = date
        return calendar[part]
    }
}