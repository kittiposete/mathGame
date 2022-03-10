package com.borntodev.mathgame

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.Exception

class GameActivity : AppCompatActivity() {
    private var btn_answer_1: Button ?= null
    private var btn_answer_2: Button ?= null
    private var btn_answer_3: Button ?= null
    private var btn_answer_4: Button ?= null
    private var tv_score_game:TextView ?= null
    private var countGame = 1
    private var score = 0
    private var wrongAnswer = 0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        try {
            countGame = intent.extras!!.getInt("countGame")+1
            score = intent.extras!!.getInt("score")
            wrongAnswer = intent.extras!!.getInt("wrongAnswer")
        }catch (e:Exception){}

        val tv_question_game = findViewById<TextView>(R.id.tv_question_game)
        val tv_count_game:TextView = findViewById(R.id.tv_count_game)
        tv_score_game = findViewById(R.id.tv_score_game)
        btn_answer_1 = findViewById(R.id.btn_answer_1)
        btn_answer_2 = findViewById(R.id.btn_answer_2)
        btn_answer_3 = findViewById(R.id.btn_answer_3)
        btn_answer_4 = findViewById(R.id.btn_answer_4)

        tv_count_game.text = "$countGame of 10"
        tv_score_game!!.text = "score: $score"

        var numberA = (1..100).random()
        var numberB = (1..100).random()
        if (numberA < numberB){
            val numberC = numberA
            numberA = numberB
            numberB = numberC
        }
        val mode = getMode()
        val answer = getAnswer(mode!!, numberA, numberB)

        tv_question_game.text = getTextQuestion(mode, numberA, numberB)
        val correctAnswer = setAnswerChoice(answer!!)

        btn_answer_1!!.setOnClickListener {
            if (correctAnswer == 1){
                correct()
            }else{
                wrong()
            }
        }
        btn_answer_2!!.setOnClickListener {
            if (correctAnswer == 2){
                correct()
            }else{
                wrong()
            }
        }
        btn_answer_3!!.setOnClickListener {
            if (correctAnswer == 3){
                correct()
            }else{
                wrong()
            }
        }
        btn_answer_4!!.setOnClickListener {
            if (correctAnswer == 4){
                correct()
            }else{
                wrong()
            }
        }
    }

    private fun correct(){
        if (countGame != 10){
            score += 10
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("countGame", countGame)
            intent.putExtra("score", score)
            intent.putExtra("wrongAnswer", wrongAnswer)
            startActivity(intent)
            finish()
        }else{
            val intent = Intent(this, EndgameActivity::class.java)
            intent.putExtra("score", score)
            intent.putExtra("wrongAnswer", wrongAnswer)
            startActivity(intent)
            finish()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun wrong(){
        Toast.makeText(this, "wrong", Toast.LENGTH_SHORT).show()
        score -= 10
        wrongAnswer += 1
        tv_score_game!!.text = "score: $score"
    }

    private fun getMode(): String? {
        val randomNumber = (0..2).random()
        when(randomNumber){
            0 -> return "plus"
            1 -> return "minus"
            2 -> return "multiply"
        }
        return null
    }

    private fun getAnswer(mode: String, numberA: Int, numberB: Int): Int? {
        when(mode){
            "plus" -> return numberA + numberB
            "minus" -> return numberA - numberB
            "multiply" -> return numberA * numberB
        }
        return null
    }

    private fun getTextQuestion(mode: String, numberA: Int, numberB: Int): String {
        var modeIcon = ""
        when(mode){
            "plus" -> modeIcon = "+"
            "minus" -> modeIcon = "-"
            "multiply" -> modeIcon = "x"
        }
        return "$numberA $modeIcon $numberB = ?"
    }

    private fun setAnswerChoice(answer: Int): Int {
        val answerChoice = ArrayList<Int>()
        val answerChoicePosition = (1..4).random()
        for (i in 1..4){
            answerChoice.add(answer-(answerChoicePosition-i))
        }

        Log.d("myDebug", answerChoice.toString())
        Log.d("myDebug", answerChoicePosition.toString())
        Log.d("myDebug", answer.toString())

        btn_answer_1!!.text = answerChoice[0].toString()
        btn_answer_2!!.text = answerChoice[1].toString()
        btn_answer_3!!.text = answerChoice[2].toString()
        btn_answer_4!!.text = answerChoice[3].toString()

        return answerChoicePosition
    }
}