package com.catnip.shared.utils

import android.app.DatePickerDialog
import android.content.Context
import java.util.*


/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
object DateUtils {
    fun Context.showDatePickerDialog(onDatePicked: (date: String) -> Unit) {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth -> onDatePicked.invoke(dayOfMonth.toString() + "/" + (month + 1) + "/" + year) },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }
}