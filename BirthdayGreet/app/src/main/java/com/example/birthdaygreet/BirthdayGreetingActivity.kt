package com.example.birthdaygreet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_birthday_greeting.*

// class of the BirthdayGreetingAcitivity 
class BirthdayGreetingActivity : AppCompatActivity(){
//On create method starts here :
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//The BirthdayGreetingActivity's UI is linked to the kotlin code here :
        setContentView(R.layout.activity_birthday_greeting)
//variable defined to store the value of the key passed through intent to this activity..
        val name = intent.getStringExtra("name")
//setting the textview's text to birthday wish with name of the person :
        birthdayGreeting.text = "Happy Birthday\n$name !!"
    }
}
