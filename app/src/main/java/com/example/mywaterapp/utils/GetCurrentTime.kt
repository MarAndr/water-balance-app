package com.example.mywaterapp.utils

import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter

fun getCurrentTime(): String{
    val dateFormat = DateTimeFormatter.ofPattern("HH:mm, dd.MM.yyyy").withZone(ZoneId.systemDefault())
    return dateFormat.format(Instant.now())
}

fun getCurrentDay(): String{
    val dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy").withZone(ZoneId.systemDefault())
    return dateFormat.format(Instant.now())
}

fun getDayFromFullTime(time: String): String{
    val list = time.split(',')
    return list[1].trim()
}