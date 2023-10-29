package uk.co.stellar.data.helpers

import java.util.Calendar
import java.util.Date

fun getDate(year: Int, month: Int, day: Int): Date {
    val calendar = Calendar.getInstance()

    calendar.set(Calendar.YEAR, year)
    calendar.set(Calendar.MONTH, month.dec())
    calendar.set(Calendar.DAY_OF_MONTH, day)

    return calendar.time
}
