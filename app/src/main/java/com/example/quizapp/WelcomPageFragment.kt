package com.example.quizapp.com.example.quizapp

import com.example.quizapp.CatagoryFragment
import android.inputmethodservice.ExtractEditText
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
import com.example.quizapp.R
import kotlinx.android.synthetic.main.welcomepage_fragment.*

class WelcomPageFragment : Fragment() {
    lateinit var nameText: TextView
    lateinit var editText: ExtractEditText
    lateinit var button1: Button
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

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

button1.setOnClickListener{
    val catagoryFragment = com.example.quizapp.CatagoryFragment()
    val transaction2 = activity?.supportFragmentManager?.beginTransaction()

    transaction2?.replace(R.id.frameLayout, catagoryFragment, "pinkFragment")
    if (transaction2 != null) {
        transaction2.commit()
    }

}

        return view
        }


    }



