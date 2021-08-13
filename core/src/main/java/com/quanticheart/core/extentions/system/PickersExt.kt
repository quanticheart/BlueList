package com.quanticheart.core.extentions.system
/* ktlint-disable no-wildcard-imports */
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.content.Context
import com.quanticheart.core.R
import java.text.SimpleDateFormat
import java.util.*

fun Context.showDatePicker(callback: (date: String) -> Unit) {
    val myCalendar = Calendar.getInstance()

    val date =
        OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, monthOfYear)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "yyyy/MM/dd"
            val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
            callback(sdf.format(myCalendar.time))
        }

    DatePickerDialog(
        this, date, myCalendar
            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
        myCalendar.get(Calendar.DAY_OF_MONTH)
    ).show()
}

fun Context.showTimePicker(callback: (time: String) -> Unit) {
    val myCalendar = Calendar.getInstance()
    val hour = myCalendar[Calendar.HOUR_OF_DAY]
    val minute = myCalendar[Calendar.MINUTE]
    val mTimePicker = TimePickerDialog(
        this, { _, selectedHour, selectedMinute ->
            callback("$selectedHour:$selectedMinute:00")
        }, hour, minute, true
    )
    mTimePicker.setTitle(getString(R.string.label_select_hour))
    mTimePicker.show()
}