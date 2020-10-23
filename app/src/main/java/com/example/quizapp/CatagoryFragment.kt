package com.example.quizapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.quizapp.com.example.quizapp.GeneralKnowledge
import com.example.quizapp.com.example.quizapp.WelcomPageFragment


class CatagoryFragment : Fragment() {

    lateinit var image1 : ImageView
    lateinit var image2 : ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.catagory_fragment, container, false)
        image1 = view.findViewById(R.id.imageView3)
        image2=view.findViewById(R.id.imageView4)

        image1.setOnClickListener {
            val intent = Intent(activity, QuizQuestion::class.java)
            startActivity(intent)
            activity?.finish()
        }

        image2.setOnClickListener{
            val intent = Intent(activity, GeneralKnowledge::class.java)
            startActivity(intent)
            //activity?.finish()
        }

        return view
    }

}