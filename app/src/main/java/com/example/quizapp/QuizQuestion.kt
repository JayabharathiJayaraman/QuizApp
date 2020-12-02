package com.example.quizapp

import android.app.PendingIntent.getActivity
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_question.*
import kotlinx.android.synthetic.main.activity_quiz_question.timer
import kotlinx.android.synthetic.main.activity_result.*
import kotlinx.android.synthetic.main.welcomepage_fragment.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.text.*

class QuizQuestion :  AppCompatActivity(), View.OnClickListener  {
    private var mCurrentPosition: Int = 1
   //private var mQuestionList: ArrayList<Question>? = null

    private lateinit var mQuestionList : List<Catagory1Questions>

    private var mSelectedOptionPosition: Int = 0
    private var mCorrectAnswer: Int = 0

    private var mCountDownTimer: CountDownTimer? = null
    private var mWrongAnswer: Int = 0
    private var mTimeOutQuestion: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN


        deleteCatagory1Questions()
        insertCatagory1QuestionsToDb()
        getCatagory1Questions()
        setQuestion()
        startTimer()
        textViewOptionOne.setOnClickListener(this)
        textViewOptionTwo.setOnClickListener(this)
        textViewOptionThree.setOnClickListener(this)
        textViewOptionFour.setOnClickListener(this)
        button2.setOnClickListener(this)

    }
    fun deleteCatagory1Questions(){
        val deleteQuestions = QuizDatabase.getAppDatabase(applicationContext)?.quizDao()
        deleteQuestions?.deleteCatagory1Questions()
    }



fun insertCatagory1QuestionsToDb()
{
    val quizDao= QuizDatabase.getAppDatabase(applicationContext)?.quizDao()
    val qus1=  Catagory1Questions(0,"1.What are the two languages that Android Studio supports","Kotlin and Phython","Kotlin and Java","Kotlin and Php","kotlin and JavaScript",2)
    val qus2= Catagory1Questions(1,"2.You can create an emulator to simulate the configuration of a particular type of Android device using a tool like ___.","Theme Editor","Android SDK Manager","AVD Manager","Virtual Editor",3)
    val qus3=Catagory1Questions(2,"3.Which file do you alter the image displayed by the ImageView in?","AndroidManifest.xml","MainActivity","Activity_Main.xml","Drawable",3)
    val qus4=Catagory1Questions(3,"4.Which component property should be changed to a name that is specific of the components use?","Content Description","ID","Text","Editable",2)
    val qus5=Catagory1Questions(4,"5.Which listener is called for the device to register the enter key press?","OnClickListener","OnHoverListener","OnContextClickListener","OnKeyListener",4)
    quizDao?.insertAndroidQuestions(qus1)
    quizDao?.insertAndroidQuestions(qus2)
    quizDao?.insertAndroidQuestions(qus3)
    quizDao?.insertAndroidQuestions(qus4)
    quizDao?.insertAndroidQuestions(qus5)
}

    fun getCatagory1Questions() {

        val getquestions=  QuizDatabase.getAppDatabase(applicationContext)?.quizDao()
        val h = getquestions?.getAndroidQuestions()
        Log.d("h","$h")
        if (h != null) {
            mQuestionList = h
        }

        }


    private fun setQuestion() {

        val question = mQuestionList!![mCurrentPosition - 1]
        defaultOptionsView()
        if (mCurrentPosition == mQuestionList!!.size) {
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
        textViewOptionFour.text = question.optionfour
    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        options.add(0, textViewOptionOne)
        options.add(1, textViewOptionTwo)
        options.add(2, textViewOptionThree)
        options.add(3, textViewOptionFour)
        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
        }

    }

    //added timer to answer the questions
    fun startTimer() {
        val toast = Toast.makeText(this, "Time's Up", Toast.LENGTH_SHORT)
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
        when (v?.id) {
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
                            intent.putExtra(Constants.Correct_Answers, mCorrectAnswer)
                            intent.putExtra(Constants.TimeOut_Answers, mTimeOutQuestion)
                            intent.putExtra(Constants.Total_Questions, mQuestionList!!.size)
                            intent.putExtra(Constants.Wrong_Answers,mWrongAnswer)
                            startActivity(intent)
                            mCountDownTimer?.cancel()

                        }
                    }
                } else {question ()
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

    fun question (){
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

    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> textViewOptionOne.background = ContextCompat.getDrawable(this, drawableView)
            2 -> textViewOptionTwo.background = ContextCompat.getDrawable(this, drawableView)
            3 -> textViewOptionThree.background = ContextCompat.getDrawable(this, drawableView)
            4 -> textViewOptionFour.background = ContextCompat.getDrawable(this, drawableView)
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
    }
}




