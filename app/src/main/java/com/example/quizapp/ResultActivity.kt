package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        val totalQuestions = intent.getIntExtra(Constants.Total_Questions, 0)
        val correctAnswer = intent.getIntExtra(Constants.Correct_Answers, 0)
        val wrongAnswer = intent.getIntExtra(Constants.Wrong_Answers,0)
        val timeOutAnswer = intent.getIntExtra(Constants.TimeOut_Answers,0)

        // Read Data from Room DB to display user name
        val userDao = QuizDatabase.getAppDatabase(applicationContext)?.quizDao()
        val list =  userDao?.getUserData()

         val sb = StringBuffer()
        list?.forEach {

            sb.append(it.toString())

        }
        textView7.text = sb.toString()
        textView8.text = "Your Score is $correctAnswer out of $totalQuestions"
        timeOutTextView.text = "Time out questions $timeOutAnswer"
        wrongAnswerView.text = "Wrong Answers $wrongAnswer"
        button3.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }
}