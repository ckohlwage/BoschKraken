package com.kohlwage.boschkraken.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {

    fun getTimeFromMillies(time: Long): String =
        Calendar.getInstance()
            .let { calendar ->
                calendar.timeInMillis = time
                SimpleDateFormat("hh:mm:ss", Locale.getDefault()).format(calendar.time)
            }

    fun getDateTimeFromMillies(time: Long): String =
        Calendar.getInstance()
            .let { calendar ->
                calendar.timeInMillis = time
                SimpleDateFormat("DD.MM.yyyy hh:mm:ss", Locale.getDefault()).format(calendar.time)
            }
}