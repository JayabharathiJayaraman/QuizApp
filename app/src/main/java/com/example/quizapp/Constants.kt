package com.example.quizapp

import com.example.quizapp.QuizQuestion


object Constants {

    const val User_Name: String = "user_name"
    const val Total_Questions: String = "total_question"
    const val Correct_Answers: String = "corrects_answers"
    fun getQuestions(): ArrayList<Question>{
    val questionList = ArrayList<Question>()
        val question1 = Question(1, "What is 1+1" ,R.drawable.quizpic, "3", "4", "2", "20",
            3)
        questionList.add(question1)

          val question2 = Question(1, "What is 4+5" ,R.drawable.quizpic, "3", "9", "6", "20",
              2)
        questionList.add(question2)
        return questionList
    }

}