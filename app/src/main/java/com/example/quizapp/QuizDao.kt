package com.example.quizapp

import androidx.room.*


@Dao
interface QuizDao {

//User Name

    @Query("SELECT * FROM user")
    fun getUserData(): List<User>

    @Query("DELETE FROM user")
    fun deleteUser()

    @Insert
    fun insertUser(user: User)

    //Catagory1
    @Query("SELECT * FROM androidQuestions")
    fun getAndroidQuestions(): List<Catagory1Questions>

    @Insert
    fun insertAndroidQuestions(quizAndroidcatagory: Catagory1Questions)

    @Query("DELETE FROM androidQuestions")
    fun deleteCatagory1Questions()

    //Catagory2
    @Query("SELECT * FROM gkQuestions")
    fun getGkQuestions(): List<Catagory2Questions>

    @Insert
    fun insertGkQuestions(quizGkcatagory: Catagory2Questions)

    @Query("DELETE FROM gkQuestions")
    fun deleteCatagory2Questions()

}