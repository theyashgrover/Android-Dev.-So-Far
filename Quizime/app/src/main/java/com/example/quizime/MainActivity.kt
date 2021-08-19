 package com.example.quizime

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

 class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.decorView.systemUiVisibility  = View.SYSTEM_UI_FLAG_FULLSCREEN
        submitbtn.setOnClickListener{
           if (eett_name.text.toString().isEmpty()){
               Toast.makeText(this,"Please Enter A Valid Name",Toast.LENGTH_SHORT).show()
           }else{
                val intent = Intent(this , QuizQuestionsActivity::class.java)
               intent.putExtra(Constants.USER_NAME , eett_name.text.toString())
               startActivity(intent)
               finish()
           }
        }
    }
}