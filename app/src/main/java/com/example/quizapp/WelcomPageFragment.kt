package com.example.quizapp.com.example.quizapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.quizapp.*
import kotlinx.android.synthetic.main.activity_result.*
import kotlinx.android.synthetic.main.welcomepage_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext


class WelcomPageFragment : Fragment()  , CoroutineScope {

    private lateinit var job : Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    lateinit var nameText: TextView
    private lateinit var editText: EditText
public lateinit var button1: Button
lateinit var  quizDatabase : QuizDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        job = Job()
        //quizDatabase = Room.databaseBuilder(activity?.applicationContext!!,QuizDatabase::class.java,"Quiz_Database").fallbackToDestructiveMigration().build()
        val view = inflater.inflate(R.layout.welcomepage_fragment, container, false)
        //return super.onCreateView(inflater, container, savedInstanceState)
        nameText = view.findViewById(R.id.textView)
        editText = view.findViewById(R.id.extractEditText)
        button1 = view.findViewById(R.id.button)

        editText.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.toString().trim({ it <= ' ' }).isEmpty()) {

                    button1.setEnabled(false)
                } else {
                    button1.setEnabled(true)
                }
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int,
                after: Int
            ) {
                // TODO Auto-generated method stub
            }

            override fun afterTextChanged(s: Editable) {
                // TODO Auto-generated method stub
            }
        })

        editText.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->

            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                val catagoryFragment = com.example.quizapp.CatagoryFragment()
                val transaction2 = activity?.supportFragmentManager?.beginTransaction()

                transaction2?.replace(R.id.frameLayout, catagoryFragment, "pinkFragment")

                if (transaction2 != null) {
                    transaction2.commit()
                }
                return@OnKeyListener true
            }
            false
        })

        button1.setOnClickListener {
            val name = editText.text.toString()
            val user =User(0, name)
            deleteUser()
            insertUserDataToDatabase(user)

            val catagoryFragment = com.example.quizapp.CatagoryFragment()
            val transaction2 = activity?.supportFragmentManager?.beginTransaction()

            transaction2?.replace(R.id.frameLayout, catagoryFragment, "pinkFragment")
            if (transaction2 != null) {
                transaction2.commit()
            }

        }

        return view

    }
    fun deleteUser(){

        val deleteUser = QuizDatabase.getAppDatabase(activity?.applicationContext!!)?.quizDao()
        deleteUser?.deleteUser()

    }

     fun insertUserDataToDatabase(user: User) {
         Log.d("!!!","success")
         val quizDao= QuizDatabase.getAppDatabase(activity?.applicationContext!!)?.quizDao()
        // val quizUserName =editText.text
        //val quizDao= activity?.let { QuizDatabase.getAppDatabase(it.applicationContext)?.quizDao() }
        //val user = User(0,user)
         quizDao?.insertUser(user)
         Log.d("!!!","$user")
    }
    /*fun saveUser(user: User) {
        launch(Dispatchers.IO) {
            quizDatabase.quizDao().insertUser(user)
        }
    }*/
}







