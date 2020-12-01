package com.example.quizapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface QuizDao {

    //@Query("SELECT * FROM user where id= :id")
    //fun getUserData(id: Long): List<User>

    @Query("SELECT * FROM user")
    fun getUserData(): List<User>

    @Query("SELECT * FROM androidQuestions ORDER BY id desc")
    fun getAndroidQuestions(): List<User>

    @Query("SELECT * FROM gkQuestions ORDER BY id desc")
    fun getGkQuestions(): List<User>

    @Insert
     fun insertUser(user: User)

    @Insert
    fun insertAndroidQuestions(quizAndroidcatagory: Catagory1Questions)

    @Insert
    fun insertGkQuestions(quizGkcatagory: Catagory2Questions)
}