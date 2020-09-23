package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Color.parseColor
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_question.*

class QuizQuestion : AppCompatActivity() , View.OnClickListener{

    private var mCurrentPosition :  Int =1
    private var mQuestionList: ArrayList<Question> ?= null
    private var mSelectedOptionPosition : Int = 0
    private var mCorrectAnswer : Int = 0
private var mUserName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        mUserName = intent.getStringExtra(Constants.User_Name)
       // val questionList = Constants.getQuestions()
        mQuestionList = Constants.getQuestions()
       // Log.d("!!!", "Hej")
        setQuestion()
        textViewOptionOne.setOnClickListener(this)
        textViewOptionTwo.setOnClickListener(this)
        textViewOptionThree.setOnClickListener(this)
        textViewOptionFour.setOnClickListener(this)
        button2.setOnClickListener(this)

    }

    private fun setQuestion(){
        //mCurrentPosition = 1
        val question = mQuestionList !! [mCurrentPosition - 1]
        defaultOptionsView()
        if(mCurrentPosition == mQuestionList!!.size)
        {
            button2.text = "FINISH"
        } else {
            button2.text = "SUBMIT"
        }
        progressBar.progress = mCurrentPosition
        textView4.text = "$mCurrentPosition" + "/" + progressBar.max
        textView3.text = question!!.question
        textViewOptionOne.text = question.optionOne
        textViewOptionTwo.text = question.optionTwo
        textViewOptionThree.text = question.optionThree
        textViewOptionFour.text = question.optionFour
    }
    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        options.add(0, textViewOptionOne)
        options.add(1, textViewOptionTwo)
        options.add(2, textViewOptionThree)
        options.add(3, textViewOptionFour)
        for(option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface  = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
        }

    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.textViewOptionOne -> {
                selectedOptionView(textViewOptionOne, 1)
            }
            R.id.textViewOptionTwo -> {
                selectedOptionView(textViewOptionTwo, 2)
            }
            R.id.textViewOptionThree -> {
                selectedOptionView(textViewOptionThree, 3)
            }
            R.id.textViewOptionFour -> {
                selectedOptionView(textViewOptionFour, 4)
            }
            R.id.button2 -> {
                 if(mSelectedOptionPosition == 0 )
                 {
                     mCurrentPosition++
                     when{
                         mCurrentPosition <= mQuestionList!!.size -> {
                             setQuestion()
                         } else -> {
                         val intent = Intent(this, ResultActivity::class.java)
                         intent.putExtra(Constants.User_Name, mUserName)
                         intent.putExtra(Constants.Correct_Answers, mCorrectAnswer)
                         intent.putExtra(Constants.Total_Questions, mQuestionList!!.size)
                         startActivity(intent)
                         finish()

                         //Toast.makeText(this, "Congrats! You have completed the quiz!", Toast.LENGTH_SHORT).show()

                     }
                     }
                 } else {
                     val question = mQuestionList?.get(mCurrentPosition-1)
                     if (question!!.correctAnswer != mSelectedOptionPosition)
                     {
                         answerView(mSelectedOptionPosition,R.drawable.wrong_option_border_bg)
                     } else {
                         mCorrectAnswer ++
                     }
                     answerView(question.correctAnswer, R.drawable.correct_option_border_bg)
                     if(mCurrentPosition == mQuestionList!!.size)
                     {
                         button2.text = "FINISH"
                     } else {
                            button2.text = "GO TO NEXT QUESTION"
                     }
                     mSelectedOptionPosition = 0
                 }
            }

        }
    }
private fun answerView(answer: Int, drawableView:Int){
    when(answer){
        1-> textViewOptionOne.background = ContextCompat.getDrawable(this, drawableView)
        2-> textViewOptionTwo.background = ContextCompat.getDrawable(this, drawableView)
        3-> textViewOptionThree.background = ContextCompat.getDrawable(this, drawableView)
        4-> textViewOptionFour.background = ContextCompat.getDrawable(this, drawableView)
    }
}
    private fun selectedOptionView (tv: TextView, selectedOptionNum: Int){
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
    }
}