package com.example.quizapp

import android.text.Editable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user")
data class User(@PrimaryKey(autoGenerate = true) val id: Int,
                @ColumnInfo(name = "name") val name: String
)

@Entity(tableName = "androidQuestions")
data class Catagory1Questions (@PrimaryKey(autoGenerate = true) val id: Int?= null,
                      @ColumnInfo(name = "question")var question: String,
                      @ColumnInfo(name = "optionOne")var optionOne: String,
                      @ColumnInfo(name = "optionTwo")var optionTwo: String,
                      @ColumnInfo(name = "optionThree")var optionThree: String,
                      @ColumnInfo(name = "optionfour")var optionfour: String,
                      @ColumnInfo(name = "correctAnswer")var correctAnswer: Int
                      )

@Entity(tableName = "gkQuestions")
data class Catagory2Questions (@PrimaryKey(autoGenerate = true) val id: Int?= null,
                               @ColumnInfo(name = "question")var question: String?,
                               @ColumnInfo(name = "optionOne")var optionOne: String,
                               @ColumnInfo(name = "optionTwo")var optionTwo: String,
                               @ColumnInfo(name = "optionThree")var optionThree: String,
                               @ColumnInfo(name = "optionfour")var optionfour: String,
                               @ColumnInfo(name = "correctAnswer")var correctAnswer: Int
)