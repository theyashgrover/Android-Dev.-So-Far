package com.example.birthdaygreet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

//class starts here :
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //the kotlin code is linked to activity_main.xml file here:
        setContentView(R.layout.activity_main)
    }
 //function for creating the birthday card :
    fun createBirthdayCard(view: View) {
        //variable to store the name being input by the user :
        val name = nameInput.editableText.toString()
        //passing the intent to another activity (the one responsible for creating the birthday card)..
        val intent = Intent(this,BirthdayGreetingActivity::class.java)
        //passing the name with the help of a keyword..
        intent.putExtra("name",name)
        startActivity(intent)

    }
}
