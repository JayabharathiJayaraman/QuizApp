package com.example.quizapp.com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.quizapp.*
import kotlinx.android.synthetic.main.activity_quiz_question.*
import java.util.*
import kotlin.collections.ArrayList

class GeneralKnowledge : AppCompatActivity() , View.OnClickListener{
    private var mCurrentPosition :  Int =1
    //private var mQuestionList: ArrayList<Question> ?= null
    private lateinit var mQuestionList : List<Catagory2Questions>
    private var mSelectedOptionPosition : Int = 0
    private var mCorrectAnswer : Int = 0
    private var mCountDownTimer: CountDownTimer? = null
    private var mTimeOutQuestion: Int = 0
    private var mWrongAnswer: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        //mQuestionList = ConstantsGK.getQuestions()

        deleteCatagory2Questions()
        insertGkQuestionsToDB()
        getCatagory2Questions()
        setQuestion()
        startTimer()
        textViewOptionOne.setOnClickListener(this)
        textViewOptionTwo.setOnClickListener(this)
        textViewOptionThree.setOnClickListener(this)
        textViewOptionFour.setOnClickListener(this)
        button2.setOnClickListener(this)

    }

    fun deleteCatagory2Questions(){
        val deleteQuestions = QuizDatabase.getAppDatabase(applicationContext)?.quizDao()
        deleteQuestions?.deleteCatagory2Questions()
    }

    fun insertGkQuestionsToDB()
    {
        val quizDao= QuizDatabase.getAppDatabase(applicationContext)?.quizDao()
        val qus1= Catagory2Questions(0,"1.What is the smallest country in the world?","USA","Vatican City","China","India",2)
        val qus2= Catagory2Questions(1,"2.What is the hottest continent on Earth?","Asia","Antarctica","North America","Africa",4)
        val qus3= Catagory2Questions(2,"3.What is the longest river in the world?","River Nile","Yellow River","Congo River","Lena River",1)
        val qus4= Catagory2Questions(3,"4.Who won the FIFA Women's World Cup in 2019?","New zealand","Austrialia","USA","Brazil",3)
        val qus5= Catagory2Questions(4,"5.Which planet is closest to the sun?","Venus","Mercury","Saturne","Neptune",2)
        quizDao?.insertGkQuestions(qus1)
        quizDao?.insertGkQuestions(qus2)
        quizDao?.insertGkQuestions(qus3)
        quizDao?.insertGkQuestions(qus4)
        quizDao?.insertGkQuestions(qus5)

    }
    fun getCatagory2Questions() {

        val getquestions=  QuizDatabase.getAppDatabase(applicationContext)?.quizDao()
        val h = getquestions?.getGkQuestions()
        Log.d("h","$h")
        if (h != null) {
            mQuestionList = h
        }

    }

    private fun setQuestion(){
        //mCurrentPosition = 1
        val question = mQuestionList!![mCurrentPosition - 1]
        defaultOptionsView()
        if(mCurrentPosition == mQuestionList!!.size)
        {
            button2.text="FINISH"
        }else
        {
            button2.text = "SUBMIT"
        }
        progressBar.progress = mCurrentPosition
        textView4.text = "$mCurrentPosition" + "/" + progressBar.max
        textView3.text = question!!.question
        textViewOptionOne.text = question.optionOne
        textViewOptionTwo.text = question.optionTwo
        textViewOptionThree.text = question.optionThree
        textViewOptionFour.text = question.optionfour
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
    //added timer to answer the questions
    fun startTimer() {
        val toast = Toast.makeText(this, "Time Over", Toast.LENGTH_SHORT)
        mCountDownTimer = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = (millisUntilFinished / 1000)  /60
                val seconds = (millisUntilFinished / 1000) % 60
                val timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
                timer.setText(timeLeftFormatted)
            }


            override fun onFinish() {
                toast.show()
                question()
                mTimeOutQuestion++
            }


        }.start()

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
                mCountDownTimer?.cancel()
                if (mSelectedOptionPosition == 0) {
                    mCurrentPosition++
                    startTimer()

                    when {
                        mCurrentPosition <= mQuestionList!!.size -> {
                            setQuestion()
                        }
                        else -> {
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(ConstantsGK.Correct_Answers, mCorrectAnswer)
                            intent.putExtra(ConstantsGK.Total_Questions, mQuestionList!!.size)
                            intent.putExtra(ConstantsGK.TimeOut_Answers, mTimeOutQuestion)
                            intent.putExtra(ConstantsGK.Wrong_Answers,mWrongAnswer)
                            startActivity(intent)
                            mCountDownTimer?.cancel()
                            //finish()

                        }
                    }
                } else { question()
                    mCountDownTimer?.cancel()
                    if (mCurrentPosition == mQuestionList!!.size) {
                        button2.text = "FINISH"
                    } else {
                        button2.text = "GO TO NEXT QUESTION"
                    }
                    mSelectedOptionPosition = 0
                }
            }

        }
    }

fun question(){
    val question = mQuestionList?.get(mCurrentPosition - 1)
    if (question!!.correctAnswer != mSelectedOptionPosition) {
        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
        mWrongAnswer++
        mCountDownTimer?.cancel()
    } else {
        mCorrectAnswer++
    }

    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

}

    private fun answerView(answer: Int, drawableView: Int){
        when(answer){
            1 -> textViewOptionOne.background = ContextCompat.getDrawable(this, drawableView)
            2 -> textViewOptionTwo.background = ContextCompat.getDrawable(this, drawableView)
            3 -> textViewOptionThree.background = ContextCompat.getDrawable(this, drawableView)
            4 -> textViewOptionFour.background = ContextCompat.getDrawable(this, drawableView)
        }
    }
    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int){
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
    }
}




