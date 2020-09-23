package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        extractEditText.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(s:CharSequence, start:Int, before:Int, count:Int) {
                if (s.toString().trim({ it <= ' ' }).isEmpty())
                {
                    button.setEnabled(false)
                }
                else
                {
                    button.setEnabled(true)
                }
            }
            override fun beforeTextChanged(s:CharSequence, start:Int, count:Int,
                                           after:Int) {
                // TODO Auto-generated method stub
            }
            override fun afterTextChanged(s: Editable) {
                // TODO Auto-generated method stub
            }
        })
        /*editText.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                //Perform Code
                return@OnKeyListener true
            }
            false
        })*/
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
    }
}