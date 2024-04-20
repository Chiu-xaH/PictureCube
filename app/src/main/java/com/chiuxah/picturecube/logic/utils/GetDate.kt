package com.chiuxah.picturecube.logic.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

object GetDate {


    val Date_yyyy_MM = SimpleDateFormat("yyyy-MM").format(Date())

    val Date_MM_dd = SimpleDateFormat("MM-dd").format(Date())

    val Date_MM = SimpleDateFormat("MM").format(Date())

    val Date_dd = SimpleDateFormat("dd").format(Date())

    val Date_yyyy = SimpleDateFormat("yyyy").format(Date())


    val Date_yyyy_MM_dd = SimpleDateFormat("yyyy-MM-dd").format(Date())

    //获取时间
    @RequiresApi(Build.VERSION_CODES.O)
    val currentTime = LocalDateTime.now()
    @RequiresApi(Build.VERSION_CODES.O)
    val formatter_Hour = DateTimeFormatter.ofPattern("HH")
    @RequiresApi(Build.VERSION_CODES.O)
    val formatter_Minute = DateTimeFormatter.ofPattern("MM")
    @RequiresApi(Build.VERSION_CODES.O)
    val formattedTime_Hour = currentTime.format(formatter_Hour)
    @RequiresApi(Build.VERSION_CODES.O)
    val formattedTime_Minute = currentTime.format(formatter_Minute)

}