package com.example.acranimecharacterroll

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rollButton: Button =
            findViewById(R.id.button) // calling the roll button from resource directory (R indicates 'res folder')
        rollButton.setOnClickListener {
            rollCharacterDice() //calling the rolldice function on button call
        }
        rollCharacterDice() //bydefault shows rolled dice ( such that If a user doesen't clicks on roll button , the app will still show rolled dice)
    }

    private fun rollCharacterDice() {
        val characterselect = Dice(8) //creating an object of (character) Dice Class
        val charRoll = characterselect.letsroll()
        val charname: TextView = findViewById(R.id.charname) //calling char name
        val charImage: ImageView =
            findViewById(R.id.imageView) //calling the find my character button from resource directory (R indicates 'res folder')..

        //Calling the charroll variable and setting the character image as per the variable..
        when (charRoll) {
            1 -> charImage.setImageResource(R.drawable.kakashi)
            2 -> charImage.setImageResource(R.drawable.ldeathnote)
            3 -> charImage.setImageResource(R.drawable.luffytaro)
            4 -> charImage.setImageResource(R.drawable.itachi)
            5 -> charImage.setImageResource(R.drawable.sasukesir)
            6 -> charImage.setImageResource(R.drawable.gonsir)
            7 -> charImage.setImageResource(R.drawable.legendgoku)
            8 -> charImage.setImageResource(R.drawable.killuasir)
        }
        charImage.contentDescription =
            charRoll.toString() //sets the content description of image as per the number that showed on dice..
        when (charRoll) {
            1 -> charname.text = "Kakashi Sensei (Naruto)"
            2 -> charname.text = "L (Death Note)"
            3 -> charname.text = "Luffy (One Piece)"
            4 -> charname.text = "Itatchi Uchiha (Naruto)"
            5 -> charname.text = "Sasuke Uchiha (Naruto)"
            6 -> charname.text = "Gon (H x H)"
            7 -> charname.text = "Goku [ANIME LEGEND] (Dragon ball)"
            8 -> charname.text = "Killua (H x H)"

        }
    }

}
    //the Dice Class containing the letsroll function..
    class Dice(private val num: Int) {

        fun letsroll(): Int {
            return (1..num).random()

    }
}