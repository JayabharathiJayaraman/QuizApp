package com.example.quizapp

import com.example.quizapp.QuizQuestion


object Constants {

    const val User_Name: String = "user_name"
    const val Total_Questions: String = "total_question"
    const val Correct_Answers: String = "corrects_answers"
    const val TimeOut_Answers: String = "timeout_answers"
    const val Wrong_Answers: String = "wrong_answers"
    fun getQuestions(): ArrayList<Question>{
    val questionList = ArrayList<Question>()
        val question1 = com.example.quizapp.Question(
            1, "1.What are the two languages that Android Studio supports", R.drawable.unnamed, "Kotlin and Phython", "Kotlin and Java", "Phython and Php", "kotlin and JavaScript",
            2
        )
        questionList.add(question1)

          val question2 = com.example.quizapp.Question(
              1, "2.You can create an emulator to simulate the configuration of a particular type of Android device using a tool like ___.", R.drawable.quizpic, "Theme Editor", "Android SDK Manager", "AVD Manager", "Virtual Edito",
              3
          )
        questionList.add(question2)

        val question3 = com.example.quizapp.Question(
            1, "3.Which file do you alter the image displayed by the ImageView in?", R.drawable.quizpic, "AndroidManifest.xml", "MainActivity", "Activity_Main.xml", "Drawable",
            3
        )
        questionList.add(question3)
        val question4 = com.example.quizapp.Question(
            1, "4.Which component property should be changed to a name that is specific of the components use?", R.drawable.quizpic, "Content Description", "ID", "Text", "Editable",
            2
        )
        questionList.add(question4)
        val question5= com.example.quizapp.Question(
            1, "5.Which listener is called for the device to register the enter key press?", R.drawable.quizpic, "OnClickListener", "OnHoverListener", "OnContextClickListener", "OnKeyListener",
            4
        )
        questionList.add(question5)
        return questionList
    }

}