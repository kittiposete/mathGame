package com.borntodev.mathgame

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class EndgameActivity : AppCompatActivity() {
    @SuppressLint("CutPasteId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_endgame)

        val score = intent.extras!!.getInt("score")
        val wrongAnswer = intent.extras!!.getInt("wrongAnswer")
        val tv_score_endgame:TextView = findViewById(R.id.tv_score_endgame)
        val tv_wrong_endgame:TextView = findViewById(R.id.tv_wrong_endgame)
        val btn_ok_endgame:Button = findViewById(R.id.btn_ok_endgame)

        tv_score_endgame.text = "score: $score"
        if(wrongAnswer == 0){
            tv_wrong_endgame.text = "no wrong answer"
        }else{
            tv_wrong_endgame.text = "wrong answer: $wrongAnswer"
        }
        btn_ok_endgame.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}