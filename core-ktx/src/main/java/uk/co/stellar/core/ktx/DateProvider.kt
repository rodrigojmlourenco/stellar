package uk.co.stellar.core.ktx

import java.util.Calendar
import java.util.Date

class DateProvider {
    fun getToday(): Date {
        val calendar = Calendar.getInstance()
        return calendar.time
    }

    fun getYesterday(): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, -1)
        return calendar.time
    }
}
