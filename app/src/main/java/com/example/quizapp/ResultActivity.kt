package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_result.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class ResultActivity : AppCompatActivity() , CoroutineScope {

    private lateinit var job : Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    lateinit var  quizDatabase : QuizDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        job = Job()
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        val totalQuestions = intent.getIntExtra(Constants.Total_Questions, 0)
        val correctAnswer = intent.getIntExtra(Constants.Correct_Answers, 0)
        val wrongAnswer = intent.getIntExtra(Constants.Wrong_Answers,0)
        val timeOutAnswer = intent.getIntExtra(Constants.TimeOut_Answers,0)

        // Read Data from Room DB to display user name
        val userDao = QuizDatabase.getAppDatabase(applicationContext)?.quizDao()
        val list =  userDao?.getUserData()

 //        val sb = StringBuffer()
        list?.forEach {

             //sb.append(it.toString())
             textView7.append("${it.name}")
            Log.d("result","${it.name}")
           // textView7.text= "${it.name}"

        }


        //quizDatabase = Room.databaseBuilder(applicationContext,QuizDatabase::class.java,"Quiz_Database").fallbackToDestructiveMigration().build()


        //val userData = loadAllItems()
        //Log.d("!!!","$userData")
        textView8.text = "Your Score is $correctAnswer out of $totalQuestions"
        timeOutTextView.text = "Time out questions $timeOutAnswer"
        wrongAnswerView.text = "Wrong Answers $wrongAnswer"
        button3.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }
   /* fun loadAllItems() : Deferred<List<User>> =

        async(Dispatchers.IO) {
            Log.d("!!!","load")
            quizDatabase.quizDao().getUserData()


        }*/
}