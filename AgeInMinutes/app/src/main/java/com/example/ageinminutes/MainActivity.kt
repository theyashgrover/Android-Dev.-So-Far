package com.example.ageinminutes

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dobButton.setOnClickListener(){view ->
            clickDatePicker(view)
        }

    }
    fun clickDatePicker(view: View){

        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{
                view, year, month, dayOfMonth ->
                Toast.makeText(this,"The chosen date of birth is $dayOfMonth/${month+1}/$year",Toast.LENGTH_LONG).show()

            val selectedDate = "$dayOfMonth/${month+1}/$year"
            tvSelectedDate.text = selectedDate

            val sdf = SimpleDateFormat("dd/mm/yyyy" , Locale.ENGLISH)
            val theDate = sdf.parse(selectedDate)
            val selectedDateInMinutes = theDate!!.time/60000
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateInMinutes = currentDate!!.time/60000
            val differenceInTime = currentDateInMinutes-selectedDateInMinutes
            tvSelectedDateInMinutes.text = differenceInTime.toString()
        }   ,year
            ,month
            ,day)
        dpd.datePicker.maxDate = Date().time - 86400000
        dpd.show()
    }
}