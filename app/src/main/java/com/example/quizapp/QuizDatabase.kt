package com.example.quizapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.quizapp.com.example.quizapp.WelcomPageFragment

@Database(entities = [User::class, Catagory1Questions::class, Catagory2Questions::class], version = 5)
//@Database(entities = [User::class, Catagory1Questions::class, Catagory2Questions::class], version = 1)
abstract class QuizDatabase: RoomDatabase() {
    abstract fun quizDao(): QuizDao

    companion object {
        private var INSTANCE : QuizDatabase? = null

        fun getAppDatabase(context: Context): QuizDatabase? {
            if(INSTANCE ==  null ) {
                INSTANCE =  Room.databaseBuilder<QuizDatabase>(
                    context.applicationContext, QuizDatabase::class.java,"quiz_database"
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()

            }

            return INSTANCE
        }
    }
}