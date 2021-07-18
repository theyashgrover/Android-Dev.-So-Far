package com.example.happyanniversaryapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.card_activity.*

class CardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.card_activity)
        val bname = intent.getStringExtra("boiname")
        val gname = intent.getStringExtra("girlname")
        textView.text = "Happy Anniversary $bname and $gname , have a lovely life Together"
    }

}