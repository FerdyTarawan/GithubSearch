package com.example.githubsearch.utils

import java.text.SimpleDateFormat
import java.util.*
import android.text.format.DateUtils
import java.text.ParseException

fun formatNumber(number: Long): String {
    if (number < 1000) {
        return number.toString()
    }

    return "${number.div(1000)}K"
}

fun formatDateToNow(dateString: String): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
    var result = dateString

    try {
        val now = System.currentTimeMillis()
        val time = sdf.parse(dateString)?.time ?: now
        result = DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS).toString()
    } catch (e: ParseException) {
        e.printStackTrace()
    }

    return result
}