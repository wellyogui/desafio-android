package br.well.tembici.common.ext

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

/**
 * Created by well_ on 05/12/2019 for tembici-challenge.
 */

const val YEAR_MONTH_DAY_HOUR = "yyyy-MM-dd'T'HH:mm:ss"

@SuppressLint("SimpleDateFormat")
fun String.toHumanDate(): String {
    val dateFormat = SimpleDateFormat(YEAR_MONTH_DAY_HOUR)
    val date = dateFormat.parse(this)
    val formattedDate = SimpleDateFormat("dd/MM/yyyy")
    return formattedDate.format(date!!)}