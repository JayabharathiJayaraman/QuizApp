package com.example.quizapp

import android.util.Log
import com.example.quizapp.Question
import com.example.quizapp.R

object ConstantsGK {

    const val User_Name: String = "user_name"
    const val Total_Questions: String = "total_question"
    const val Correct_Answers: String = "corrects_answers"
    const val TimeOut_Answers: String = "timeout_answers"
    const val Wrong_Answers: String = "wrong_answers"
    const val Skipped_Questions: String = "skipped_questions"
    fun getQuestions(): ArrayList<Question>{
        val questionList = ArrayList<Question>()
        val question1 = com.example.quizapp.Question(
            1, "1.What is the smallest country in the world?", R.drawable.unnamed, "Kotlin and Phython", "Vatican City", "Phython and Php", "kotlin and JavaScript",
            2
        )
        questionList.add(question1)

        val question2 = com.example.quizapp.Question(
            1, "2.What is the hottest continent on Earth?", R.drawable.quizpic, "Theme Editor", "Android SDK Manager", "AVD Manager", "Africa",
            4
        )
        questionList.add(question2)

        val question3 = com.example.quizapp.Question(
            1, "3.What is the longest river in the world?", R.drawable.quizpic, "River Nile", "MainActivity", "Activity_Main.xml", "Drawable",
            1
        )
        questionList.add(question3)
        val question4 = com.example.quizapp.Question(
            1, "4.Who won the FIFA Women's World Cup in 2019?", R.drawable.quizpic, "Content Description", "ID", "USA", "Editable",
            3
        )
        questionList.add(question4)
        val question5= com.example.quizapp.Question(
            1, "5.Which planet is closest to the sun?", R.drawable.quizpic, "OnClickListener", "Mercury", "OnContextClickListener", "OnKeyListener",
            2
        )
        questionList.add(question5)
        return questionList
    }

}