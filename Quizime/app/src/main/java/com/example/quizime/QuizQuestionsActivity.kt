   package com.example.quizime

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_questions.*

class QuizQuestionsActivity : AppCompatActivity() , View.OnClickListener{

    private var mCurrentPosition:Int = 1 //used for assigning the current question
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0 //used for selecting the position of option
    private var mCorrectAnswers : Int = 0 // for your score :)
    private var mUserName : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        mUserName = intent.getStringExtra(Constants.USER_NAME)

        mQuestionsList = Constants.getQuestions()

        setQuestion()

        tv_option_one.setOnClickListener(this)
        tv_option_two.setOnClickListener(this)
        tv_option_three.setOnClickListener(this)
        tv_option_four.setOnClickListener(this)
        quesubmit.setOnClickListener(this)
    }

    private fun setQuestion(){

        val question = mQuestionsList!!.get(mCurrentPosition-1)
        defaultOptionsView()

        if(mCurrentPosition == mQuestionsList!!.size){
            quesubmit.text == "FINISH"
        }else{
            quesubmit.text == "SUBMIT"
        }

        //set the xml attribute "progress" of progressBar (xml id of progressbar) eqv. to currentPosition..
        progressBar.progress=mCurrentPosition
        //the text horizontally right to progressbar
        tv_progress.text = "$mCurrentPosition" + "/" +progressBar.max
        //set the question text
        tv_question.text = question!!.question
        //set the image of question
        iv_image.setImageResource(question.image)
        //set the options one by one
        tv_option_one.text = question.optionOne
        tv_option_two.text = question.optionTwo
        tv_option_three.text = question.optionThree
        tv_option_four.text = question.optionFour
    }

    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()

        options.add(0 , tv_option_one)
        options.add(1 , tv_option_two)
        options.add(2 , tv_option_three)
        options.add(3 , tv_option_four)

        for(option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT //setting the typeface (bold/italic/default appearance)
            option.background = ContextCompat.getDrawable(this , R.drawable.default_shape)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_option_one -> {
                selectedOptionView(tv_option_one, 1)
            }
            R.id.tv_option_two -> {
                selectedOptionView(tv_option_two, 2)
            }
            R.id.tv_option_three -> {
                selectedOptionView(tv_option_three, 3)
            }
            R.id.tv_option_four -> {
                selectedOptionView(tv_option_four, 4)
            }
            R.id.quesubmit -> {
                if (mSelectedOptionPosition == 0) {
                    mCurrentPosition++

                    when {
                        mCurrentPosition <= mQuestionsList!!.size -> {
                            setQuestion()
                        }
                        else -> {
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList!!.size)
                            startActivity(intent)
                            finish()
                        }

                    }
                } else {
                    val question = mQuestionsList?.get(mCurrentPosition - 1)
                    if(question!!.correctAnswer != mSelectedOptionPosition){
                        answerCorrectView(mSelectedOptionPosition,R.drawable.wrong_option_border)
                    }
                    else{
                        mCorrectAnswers++
                        answerCorrectView(mSelectedOptionPosition,R.drawable.correct_option_border)
                    }

                    if (mCurrentPosition == mQuestionsList!!.size) {
                        quesubmit.text == "FINISH"
                    } else {
                        quesubmit.text == "GO TO NEXT QUESTION"
                    }
                    mSelectedOptionPosition = 0
                }
            }
        }
    }
    private fun answerCorrectView(answer :Int , drawableView :Int){
        when(answer){
            1 -> {
                tv_option_one.background = ContextCompat.getDrawable(
                    this , drawableView
                )
            }
            2 -> {
                tv_option_two.background = ContextCompat.getDrawable(
                    this , drawableView
                )
            }
            3 -> {
                tv_option_three.background = ContextCompat.getDrawable(
                    this , drawableView
                )
            } 4 -> {
            tv_option_four.background = ContextCompat.getDrawable(
                this , drawableView
            )
        }
        }
    }

    private fun selectedOptionView(tv: TextView , selectedOptionNum : Int){
        defaultOptionsView() //resett   ing to the default view
        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface , Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this , R.drawable.selected_option_border)
    }

}