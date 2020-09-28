package com.example.quizapp


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import com.example.quizapp.com.example.quizapp.WelcomPageFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.welcomepage_fragment.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        val welcomePageFragment = WelcomPageFragment()
        val transaction = supportFragmentManager.beginTransaction()

        transaction.add(R.id.frameLayout, welcomePageFragment, "pinkFragment")
        transaction.commit()



    }


    /*editText.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
        if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
            //Perform Code
            return@OnKeyListener true
        }
        false
    })*/
    /*fun playButton(){
        button.setOnClickListener {

            if(extractEditText.text.toString().isEmpty())
            {
                Toast.makeText(this, "Please enter the name", Toast.LENGTH_SHORT).show()
            }
            else {
                val intent = Intent(this,QuizQuestion::class.java)
                intent.putExtra(Constants.User_Name, extractEditText.text.toString())
                startActivity(intent)
                finish()
            }
        }
    }*/
}


