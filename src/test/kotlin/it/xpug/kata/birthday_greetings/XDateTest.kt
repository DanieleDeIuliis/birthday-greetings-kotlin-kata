package it.xpug.kata.birthday_greetings

import it.xpug.kata.birthday_greetings.domain.XDate
import org.junit.Assert.*
import org.junit.Test

class XDateTest {

    @Test
    fun getters() {
        val date = XDate("1789/01/24")
        assertEquals(1, date.month.toLong())
        assertEquals(24, date.day.toLong())
    }

    @Test
    fun isSameDate() {
        val date = XDate("1789/01/24")
        val sameDay = XDate("2001/01/24")
        val notSameDay = XDate("1789/01/25")
        val notSameMonth = XDate("1789/02/25")

        assertTrue("same", date.isSameDay(sameDay))
        assertFalse("not same day", date.isSameDay(notSameDay))
        assertFalse("not same month", date.isSameDay(notSameMonth))
    }

    @Test
    fun equality() {
        val base = XDate("2000/01/02")
        val same = XDate("2000/01/02")
        val different = XDate("2000/01/04")

        assertFalse(base.equals(null))
        assertFalse(base.equals(""))
        assertTrue(base.equals(base))
        assertTrue(base.equals(same))
        assertFalse(base.equals(different))
    }
}