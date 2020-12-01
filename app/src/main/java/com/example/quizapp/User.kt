package com.example.quizapp

import android.inputmethodservice.ExtractEditText
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user")
data class User(@PrimaryKey(autoGenerate = true) val id: Int=0,
                @ColumnInfo(name = "name") val name: ExtractEditText
)

@Entity(tableName = "androidQuestions")
data class Catagory1Questions (@PrimaryKey(autoGenerate = true) val id: Int,
                      @ColumnInfo(name = "question")var question: String?,
                      @ColumnInfo(name = "optionOne")var optionOne: String,
                      @ColumnInfo(name = "optionTwo")var optionTwo: String,
                      @ColumnInfo(name = "optionThree")var optionThree: String,
                      @ColumnInfo(name = "optionfour")var optionfour: String
                      )

@Entity(tableName = "gkQuestions")
data class Catagory2Questions (@PrimaryKey(autoGenerate = true) val id: Int,
                               @ColumnInfo(name = "question")var question: String?,
                               @ColumnInfo(name = "optionOne")var optionOne: String,
                               @ColumnInfo(name = "optionTwo")var optionTwo: String,
                               @ColumnInfo(name = "optionThree")var optionThree: String,
                               @ColumnInfo(name = "optionfour")var optionfour: String
)