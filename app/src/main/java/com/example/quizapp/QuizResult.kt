package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class QuizResult : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_result)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        val txtUserName: TextView = findViewById(R.id.txtUsername)
        val txtScore: TextView = findViewById(R.id.txtScore)
        val btnFinish: Button = findViewById(R.id.btnFinish)

        val userName = intent.getStringExtra(Constant.USER_NAME)
        txtUserName.text = userName

        val totalQuestion = intent.getIntExtra(Constant.TOTAL_QUESTION, 0)
        val correctAnswers = intent.getIntExtra(Constant.CORRECT_ANSWER, 0)

        txtScore.text = "Your score is $correctAnswers out of $totalQuestion"

        btnFinish.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


    }
}