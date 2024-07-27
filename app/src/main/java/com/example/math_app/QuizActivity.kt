package com.example.math_app

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random

class QuizActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        val countdown_text: TextView = findViewById(R.id.countdown)
        val level_text: TextView = findViewById(R.id.level)
        val menu: Button = findViewById(R.id.menu)
        val example_text: TextView = findViewById(R.id.example)

        val answer_a: Button = findViewById(R.id.answer_a)
        val answer_b: Button = findViewById(R.id.answer_b)
        val answer_c: Button = findViewById(R.id.answer_c)
        val answer_d: Button = findViewById(R.id.answer_d)

        val level = 1
        val abcd = List(1) { Random.nextInt(1,5)}



        if (level <= 2){
            val values_a = List(2) { Random.nextInt(1, 10) }
            val resula_a = values_a[0]
            val result_b = values_a[1]
            val values_c = resula_a + result_b

            if (abcd[0] == 1){
                answer_a.setText("$values_c")
                answer_b.setText("${ Random.nextInt(1,20)}")
                answer_c.setText("${ Random.nextInt(1,20)}")
                answer_d.setText("${ Random.nextInt(1,20)}")
            }else if (abcd[0] == 2){
                answer_a.setText("${Random.nextInt(1, 20)}")
                answer_b.setText("$values_c")
                answer_c.setText("${ Random.nextInt(1,20)}")
                answer_d.setText("${ Random.nextInt(1,20)}")
            }else if (abcd[0] == 3){
                answer_a.setText("${Random.nextInt(1, 20)}")
                answer_b.setText("${ Random.nextInt(1,20)}")
                answer_c.setText("$values_c")
                answer_d.setText("${ Random.nextInt(1,20)}")
            }else if (abcd[0] == 4){
                answer_a.setText("${Random.nextInt(1, 20)}")
                answer_b.setText("${ Random.nextInt(1,20)}")
                answer_c.setText("${ Random.nextInt(1,20)}")
                answer_d.setText("$values_c")
            }


            example_text.setText("$resula_a + $result_b =")
            level_text.setText("Lv.$level")


        }






    }
}