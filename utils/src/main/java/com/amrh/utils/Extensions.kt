package com.amrh.challenge.utils

import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.amrh.utils.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


val dateTimeFormatFromBackend = "yyyy-MM-dd'T'HH:mm:ss'Z'"
val ourDateTimeFormation = "EEE, d MMM yyyy"

fun formatDate(inputDate: String): String {
    val input = SimpleDateFormat(dateTimeFormatFromBackend)
    val output = SimpleDateFormat(ourDateTimeFormation, Locale.US)
    try {
        return output.format(input.parse(inputDate)!!)   // format output
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return inputDate
}

fun formatTimeHHMM(inputDate: String): String {
    val input = SimpleDateFormat(dateTimeFormatFromBackend)
    val output = SimpleDateFormat("HH:mm", Locale.US)
    try {
        return output.format(input.parse(inputDate)!!)   // format output
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return inputDate
}

fun getCurrentDate(): String {
    return SimpleDateFormat(
        ourDateTimeFormation,
        Locale.getDefault()
    ).format(Date(System.currentTimeMillis()))

}
fun getCurrentDateUnixTime(): Long? {
    return formatToUnixTime( getCurrentDate())

}

fun formatToUnixTime(date: String): Long? {
    return try {
        val date = SimpleDateFormat(ourDateTimeFormation).parse(date)
        date.time
    } catch (e: Exception) {
        0
    }
}

fun formatToUnixTimeBackEndFormation(date: String): Long? {
    return try {
        val date = SimpleDateFormat(dateTimeFormatFromBackend).parse(date)
        date.time
    } catch (e: Exception) {
        0
    }
}




@RequiresApi(Build.VERSION_CODES.O)
fun isToday(startDatetime: String): Boolean {
    try {
        var formatter0 = DateTimeFormatter.ofPattern(dateTimeFormatFromBackend)
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

fun Context.showToast(message: String?) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

var isRunning = false


fun actionAfter(millSecond: Long, action: () -> Unit) {
    Handler().postDelayed({
        action()
    }, millSecond)
}
