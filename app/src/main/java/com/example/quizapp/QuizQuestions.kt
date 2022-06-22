package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

class QuizQuestions : AppCompatActivity(), View.OnClickListener {


    private var mCurrentPosition: Int = 1
    private var mQuestionList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0
    private var mCorrectAnswer: Int = 0
    private var mUserName:String? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        mUserName = intent.getStringExtra(Constant.USER_NAME)

        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        val txtProgress: TextView = findViewById(R.id.txtProgress)
        val txtQuestion: TextView = findViewById(R.id.txtQuestion)
        val quesImg: ImageView = findViewById(R.id.quesImg)
        val optionOne: TextView = findViewById(R.id.optionOne)
        val optionTwo: TextView = findViewById(R.id.optionTwo)
        val optionThree: TextView = findViewById(R.id.optionThree)
        val optionFour: TextView = findViewById(R.id.optionFour)
        val btnSubmit: Button = findViewById(R.id.btnSubmit)

        mQuestionList = Constant.getQuestion()

        setQuestion()

        optionOne.setOnClickListener(this)
        optionTwo.setOnClickListener(this)
        optionThree.setOnClickListener(this)
        optionFour.setOnClickListener(this)
        btnSubmit.setOnClickListener(this)

    }

    /* it's not working, must be declared in the same scope where it's been used
    private fun initViews(){
        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        val txtProgress: TextView = findViewById(R.id.txtProgress)
        val txtQuestion: TextView = findViewById(R.id.txtQuestion)
        val quesImg: ImageView = findViewById(R.id.quesImg)
        val optionOne: TextView = findViewById(R.id.optionOne)
        val optionTwo: TextView = findViewById(R.id.optionTwo)
        val optionThree: TextView = findViewById(R.id.optionThree)
        val optionFour: TextView = findViewById(R.id.optionFour)
    }*/

    private fun setQuestion() {

        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        val txtProgress: TextView = findViewById(R.id.txtProgress)
        val txtQuestion: TextView = findViewById(R.id.txtQuestion)
        val quesImg: ImageView = findViewById(R.id.quesImg)
        val optionOne: TextView = findViewById(R.id.optionOne)
        val optionTwo: TextView = findViewById(R.id.optionTwo)
        val optionThree: TextView = findViewById(R.id.optionThree)
        val optionFour: TextView = findViewById(R.id.optionFour)
        val btnSubmit: Button = findViewById(R.id.btnSubmit)

        setDefaultBackground()
        if (mCurrentPosition == mQuestionList!!.size) {
            btnSubmit.text = "FINISH"
        } else {
            btnSubmit.text = "SUBMIT"
        }


        val question = mQuestionList!![mCurrentPosition - 1]

        progressBar.progress = mCurrentPosition
        txtProgress.text = "$mCurrentPosition" + "/" + progressBar.max

        txtQuestion.text = question!!.question
        quesImg.setImageResource(question.image)
        optionOne.setText(question.OptionOne)
        optionTwo.setText(question.OptionTwo)
        optionThree.setText(question.OptionThree)
        optionFour.setText(question.OptionFour)
    }

    private fun setDefaultBackground() {
        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        val txtProgress: TextView = findViewById(R.id.txtProgress)
        val txtQuestion: TextView = findViewById(R.id.txtQuestion)
        val quesImg: ImageView = findViewById(R.id.quesImg)
        val optionOne: TextView = findViewById(R.id.optionOne)
        val optionTwo: TextView = findViewById(R.id.optionTwo)
        val optionThree: TextView = findViewById(R.id.optionThree)
        val optionFour: TextView = findViewById(R.id.optionFour)
        val options = ArrayList<TextView>()

        options.add(0, optionOne)
        options.add(1, optionTwo)
        options.add(2, optionThree)
        options.add(3, optionFour)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
        }
    }

    override fun onClick(v: View?) {
        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        val txtProgress: TextView = findViewById(R.id.txtProgress)
        val txtQuestion: TextView = findViewById(R.id.txtQuestion)
        val quesImg: ImageView = findViewById(R.id.quesImg)
        val optionOne: TextView = findViewById(R.id.optionOne)
        val optionTwo: TextView = findViewById(R.id.optionTwo)
        val optionThree: TextView = findViewById(R.id.optionThree)
        val optionFour: TextView = findViewById(R.id.optionFour)
        val btnSubmit: Button = findViewById(R.id.btnSubmit)

        when (v?.id) {
            R.id.optionOne -> {
                setSelectedOption(optionOne, 1)
            }
            R.id.optionTwo -> {
                setSelectedOption(optionTwo, 2)
            }
            R.id.optionThree -> {
                setSelectedOption(optionThree, 3)
            }
            R.id.optionFour -> {
                setSelectedOption(optionFour, 4)
            }
            R.id.btnSubmit -> {
                if (mSelectedOptionPosition == 0) {
                    mCurrentPosition++

                    when {
                        mCurrentPosition <= mQuestionList!!.size -> {
                            setQuestion()

                        }
                        else -> {

                            val intent = Intent(this, QuizResult::class.java)
                            intent.putExtra(Constant.USER_NAME,mUserName)
                            intent.putExtra(Constant.TOTAL_QUESTION,mQuestionList!!.size)
                            intent.putExtra(Constant.CORRECT_ANSWER,mCorrectAnswer)
                            startActivity(intent)
                            finish()
                        }
                    }
                } else {
                    val question = mQuestionList?.get(mCurrentPosition - 1)

                    if (question!!.correctAnswer != mSelectedOptionPosition) {
                        answerView(mSelectedOptionPosition, R.drawable.false_option_border_bg)
                    }else{
                        mCorrectAnswer++
                    }
                    answerView(question!!.correctAnswer, R.drawable.correct_option_boreder_bg)

                    if (mCurrentPosition == mQuestionList!!.size) {
                        btnSubmit.text = "FINISH"
                    } else {
                        btnSubmit.text = "GO TO NEXT QUESTION"
                    }
                    mSelectedOptionPosition = 0
                }
            }
        }
    }

    private fun setSelectedOption(tv: TextView, selectedOptionNum: Int) {
        setDefaultBackground()

        mSelectedOptionPosition = selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)

    }

    private fun answerView(answer: Int, drawableView: Int) {
        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        val txtProgress: TextView = findViewById(R.id.txtProgress)
        val txtQuestion: TextView = findViewById(R.id.txtQuestion)
        val quesImg: ImageView = findViewById(R.id.quesImg)
        val optionOne: TextView = findViewById(R.id.optionOne)
        val optionTwo: TextView = findViewById(R.id.optionTwo)
        val optionThree: TextView = findViewById(R.id.optionThree)
        val optionFour: TextView = findViewById(R.id.optionFour)
        val btnSubmit: Button = findViewById(R.id.btnSubmit)
        when (answer) {
            1 -> {
                optionOne.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            2 -> {
                optionTwo.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            3 -> {
                optionThree.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            4 -> {
                optionFour.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
        }

    }

}