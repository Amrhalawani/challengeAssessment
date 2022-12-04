package com.amrh.challenge.utils

import android.os.Build
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

import java.time.LocalDateTime



val dateTimeFormat = "yyyy-MM-dd'T'H:mm:ss"
fun getDateFormattedYYYYMMDD(inputDate: String): String {
    val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
    val output = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    try {
        return output.format(input.parse(inputDate)!!)   // format output
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return inputDate
}

fun getTimeFormattedHHMM(inputDate: String): String {
    val input = SimpleDateFormat("2023-05-06'T'14:00:00Z")
    val output = SimpleDateFormat("HH:mm", Locale.US)
    try {
        return output.format(input.parse(inputDate)!!)   // format output
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return inputDate
}

@RequiresApi(Build.VERSION_CODES.O)
fun isToday(startDatetime: String): Boolean {
    try {
        var formatter0 = DateTimeFormatter.ofPattern(dateTimeFormat)
        var date = LocalDateTime.parse(startDatetime, formatter0)
        val currentDate = (Calendar.getInstance() as GregorianCalendar).toZonedDateTime()
        if (date.dayOfWeek == currentDate.dayOfWeek) {
            return true
        }
    } catch (e: Exception) {
        Log.e("isToday", "${e.message.toString()}")
    }
    return false
}


fun View.inVisible() {
    visibility = View.INVISIBLE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}
