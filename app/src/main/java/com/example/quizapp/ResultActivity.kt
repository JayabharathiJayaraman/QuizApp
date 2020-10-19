package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import com.example.quizapp.com.example.quizapp.WelcomPageFragment
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        val userName = intent.getStringExtra(Constants.User_Name)
        textView7.text = "$userName"

        val totalQuestions = intent.getIntExtra(Constants.Total_Questions, 0)
        val correctAnswer = intent.getIntExtra(Constants.Correct_Answers, 0)
        val wrongAnswer = intent.getIntExtra(Constants.Wrong_Answers,0)
        val skipedAnswer = intent.getIntExtra(Constants.TimeOut_Answers,0)
        textView8.text = "Your Score is $correctAnswer out of $totalQuestions"
        skipedAnswerView.text = "Time out questions $skipedAnswer"
        wrongAnswerView.text = "Wrong Answers $wrongAnswer"
        button3.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }
}