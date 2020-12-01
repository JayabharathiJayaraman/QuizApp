package com.example.quizapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.room.RoomDatabase
import com.example.quizapp.com.example.quizapp.WelcomPageFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.welcomepage_fragment.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    // val quizDatabase : QuizDatabase
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN


        val welcomePageFragment = WelcomPageFragment()

        val transaction = supportFragmentManager.beginTransaction()

        transaction.add(R.id.frameLayout, welcomePageFragment, "pinkFragment")
        transaction.commit()

        welcomePageFragment.button.setOnClickListener {
            insertUserDataToDatabase()
            insertAndroidQuestionsToDB()
            insertGkQuestionsToDB()
        }


        //quizDatabase = Room.databaseBuilder(applicationContext,QuizDatabase::class.java,"Quiz_Database").allowMainThreadQueries().build()


    }

    fun insertUserDataToDatabase() {
        val welcomePageFragment = WelcomPageFragment()
        val quizUserName =welcomePageFragment.editText
        val quizDao= QuizDatabase.getAppDatabase(applicationContext)?.quizDao()
        val user = User(0,quizUserName)
        quizDao?.insertUser(user)
    }

    fun insertAndroidQuestionsToDB()
    {
        val quizDao= QuizDatabase.getAppDatabase(applicationContext)?.quizDao()
        val qus1=  Catagory1Questions(0,"1.What are the two languages that Android Studio supports","Kotlin and Phython","Kotlin and Java","Kotlin and Java","kotlin and JavaScript")
        val qus2= Catagory1Questions(1,"2.You can create an emulator to simulate the configuration of a particular type of Android device using a tool like ___.","Theme Editor","Android SDK Manager","AVD Manager","Virtual Editor")
        val qus3=Catagory1Questions(2,"3.Which file do you alter the image displayed by the ImageView in?","AndroidManifest.xml","MainActivity","Activity_Main.xml","Drawable")
        val qus4=Catagory1Questions(3,"4.Which component property should be changed to a name that is specific of the components use?","Content Description","ID","Text","Editable")
        val qus5=Catagory1Questions(4,"5.Which listener is called for the device to register the enter key press?","OnClickListener","OnHoverListener","OnContextClickListener","OnKeyListener")
        quizDao?.insertAndroidQuestions(qus1)
        quizDao?.insertAndroidQuestions(qus2)
        quizDao?.insertAndroidQuestions(qus3)
        quizDao?.insertAndroidQuestions(qus4)
        quizDao?.insertAndroidQuestions(qus5)
    }

    fun insertGkQuestionsToDB()
    {
        val quizDao= QuizDatabase.getAppDatabase(applicationContext)?.quizDao()
        val qus1= Catagory2Questions(0,"1.What is the smallest country in the world?","USA","Vatican City","China","India")
        val qus2= Catagory2Questions(1,"2.What is the hottest continent on Earth?","Asia","Antarctica","North America","Africa")
        val qus3= Catagory2Questions(2,"3.What is the longest river in the world?","River Nile","Yellow River","Congo River","Lena River")
        val qus4= Catagory2Questions(3,"4.Who won the FIFA Women's World Cup in 2019?","New zealand","Austrialia","USA","Brazil")
        val qus5= Catagory2Questions(4,"5.Which planet is closest to the sun?","Venus","Mercury","Saturne","Neptune")
        quizDao?.insertGkQuestions(qus1)
        quizDao?.insertGkQuestions(qus2)
        quizDao?.insertGkQuestions(qus3)
        quizDao?.insertGkQuestions(qus4)
        quizDao?.insertGkQuestions(qus5)

    }

}


