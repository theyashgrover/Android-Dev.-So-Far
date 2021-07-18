package com.example.happyanniversaryapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun createAnniversaryCard(view: View) {
        val boyname = edittextguy.editableText.toString()
        val girlname = edittextgirl.editableText.toString()
        val intent = Intent(this , CardActivity::class.java)
        intent.putExtra("boiname" , boyname)
        intent.putExtra("girlname" , girlname)
        startActivity(intent)
    }
}