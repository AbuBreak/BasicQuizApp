package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Hide the Notification bar and view a full screen size.
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        val txtUserName: EditText = findViewById(R.id.txtUserName)
        val btnStart: Button = findViewById(R.id.btnStart)

        btnStart.setOnClickListener {
                if(txtUserName.text.toString().isEmpty()){
                    Toast.makeText(this,"Please enter your name!",Toast.LENGTH_SHORT).show()
                }else{
                    val intent=Intent(this,QuizQuestions::class.java)
                    intent.putExtra(Constant.USER_NAME,txtUserName.text.toString())
                    startActivity(intent)
                    finish()

                }
        }
    }



}