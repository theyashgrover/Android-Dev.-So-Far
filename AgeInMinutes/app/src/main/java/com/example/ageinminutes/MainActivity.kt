//kotlin code for age in minutes application :
package com.example.ageinminutes
//importing necessary modules..
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
//onCreate method starts here :
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//setting the onClickListener of (Select D.O.B. button) 
        dobButton.setOnClickListener(){view ->
            clickDatePicker(view)  //calling the clickDatePicker Function..
        }

    }
    //clickDatePicker function starts here :
    fun clickDatePicker(view: View){
        //the variables myCalender , year , month and say are actually objects (instances) of class Calendar ( Which is preExisting ..)
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)
        //calling the datepickerdialog box and setting the chosen date in it to textview and using it later for calculations..
        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{
                view, year, month, dayOfMonth ->
                Toast.makeText(this,"The chosen date of birth is $dayOfMonth/${month+1}/$year",Toast.LENGTH_LONG).show() //showing a toast of selected birthdate

            val selectedDate = "$dayOfMonth/${month+1}/$year" //displaying the selected date in "dd/mm/yyyy" format
            tvSelectedDate.text = selectedDate //setting the text of empty textview as the selected date

            val sdf = SimpleDateFormat("dd/mm/yyyy" , Locale.ENGLISH) //converting into default format..
            val theDate = sdf.parse(selectedDate) //parsing the date 
            val selectedDateInMinutes = theDate!!.time/60000 //converting the dob time into minutes , (!!) operator makes this a non nullable datatype..
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis())) //this code indicates today's date..
            val currentDateInMinutes = currentDate!!.time/60000 //converting the current date into minutes , (!!) operator makes this a non nullable datatype..
            val differenceInTime = currentDateInMinutes-selectedDateInMinutes //calculating the age in minutes of the person..
            tvSelectedDateInMinutes.text = differenceInTime.toString() //setting the textview to the age in minutes (Calculated in the above step)
        }   ,year
            ,month
            ,day)
        //making sure that a person cant select a day in future..
        dpd.datePicker.maxDate = Date().time - 86400000
        //finally displaying the result..
        dpd.show()
    }
}
