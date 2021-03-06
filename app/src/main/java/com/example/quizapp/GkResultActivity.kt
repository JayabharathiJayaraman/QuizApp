package com.example.quizapp.com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.quizapp.*
import kotlinx.android.synthetic.main.activity_result.*

class GkResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN


        val totalQuestions = intent.getIntExtra(ConstantsGK.Total_Questions, 0)
        val correctAnswer = intent.getIntExtra(ConstantsGK.Correct_Answers, 0)
        val wrongAnswer = intent.getIntExtra(ConstantsGK.Wrong_Answers,0)
        val timeOutAnswer = intent.getIntExtra(ConstantsGK.TimeOut_Answers,0)

        textView8.text = "Your Score is $correctAnswer out of $totalQuestions"
        timeOutTextView.text = "Time out questions $timeOutAnswer"
        wrongAnswerView.text = "Wrong Answers $wrongAnswer"

        button3.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }
}