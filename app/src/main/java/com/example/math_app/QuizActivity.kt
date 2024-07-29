package com.example.math_app

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random

class QuizActivity : AppCompatActivity() {

    private lateinit var countdown_text: TextView
    private lateinit var level_text: TextView
    private lateinit var menu: Button
    private lateinit var example_text: TextView
    private lateinit var answer_a: Button
    private lateinit var answer_b: Button
    private lateinit var answer_c: Button
    private lateinit var answer_d: Button
    private var level = 1

    private var abcd = List(1) { Random.nextInt(1, 5) }

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)


        countdown_text = findViewById(R.id.countdown)
        level_text = findViewById(R.id.level)
        menu = findViewById(R.id.menu)
        example_text = findViewById(R.id.example)
        answer_a = findViewById(R.id.answer_a)
        answer_b = findViewById(R.id.answer_b)
        answer_c = findViewById(R.id.answer_c)
        answer_d = findViewById(R.id.answer_d)

        var firstL = true

        if (firstL == true) {
            example_text.text = "This window will show simple math examples. Below are four answers, of which only one is correct."
            example_text.textSize = 16F
            example_text.height = 130
        }

        if (abcd[0] == 1 && firstL == true) {
            answer_a.text = "I'm understad"
        }
        if (abcd[0] == 2 && firstL == true) {
            answer_b.text = "I'm understad"
        }
        if (abcd[0] == 3 && firstL == true) {
            answer_c.text = "I'm understad"
        }
        if (abcd[0] == 4 && firstL == true) {
            answer_d.text = "I'm understad"
        }

        answer_a.setOnClickListener {
            if (abcd[0] == 1 && firstL == false) {
                level += 1
            }
            nextLevel()
            firstL = false
            example_text.textSize = 45F
            example_text.height = 100
            level_text.text = "Lv.$level"
        }
        answer_b.setOnClickListener {
            if (abcd[0] == 2 && firstL == false) {
                level += 1
            }
            nextLevel()
            firstL = false
            example_text.textSize = 45F
            example_text.height = 100
            level_text.text = "Lv.$level"
        }
        answer_c.setOnClickListener {
            if (abcd[0] == 3 && firstL == false) {
                level += 1
            }
            nextLevel()
            firstL = false
            example_text.textSize = 45F
            example_text.height = 100
            level_text.text = "Lv.$level"
        }
        answer_d.setOnClickListener {
            if (abcd[0] == 4 && firstL == false) {
                level += 1
            }
            nextLevel()
            firstL = false
            example_text.textSize = 45F
            example_text.height = 100
            level_text.text = "Lv.$level"
        }
    }

    @SuppressLint("SetTextI18n")
    private fun nextLevel() {
        abcd = List(1) { Random.nextInt(1, 5) }

        if (level <= 3) {
            val from: Int = 1
            val until: Int = 10

            val from_btn = 1
            val until_btn = 10

            val plus_or_minus = List(4) { Random.nextInt(1, 3) }

            val values_a = List(2) { Random.nextInt(from, until) }
            val resula_a = values_a[0]
            val result_b = values_a[1]
            var values_c = resula_a + result_b

            val answers_4 = mutableSetOf<Int>()
            while (answers_4.size < 3) {
                answers_4.add(Random.nextInt(from_btn, until_btn))
            }
            val answers_4_list = answers_4.toList()
            var answer_1_random = answers_4_list[0] + values_c
            var answer_2_random = answers_4_list[1] + values_c
            var answer_3_random = answers_4_list[2] + values_c

            if (plus_or_minus[3] == 1){
                values_c = resula_a + result_b
            }else if(plus_or_minus[3] == 2){
                values_c = resula_a - result_b
            }

            if (plus_or_minus[0] == 1){
                answer_1_random = answers_4_list[0] + values_c
            }else if(plus_or_minus[0] == 2){
                answer_1_random = answers_4_list[0] - values_c
            }

            if (plus_or_minus[1] == 1){
                answer_2_random = answers_4_list[1] + values_c
            }else if(plus_or_minus[1] == 2){
                answer_2_random = answers_4_list[1] - values_c
            }

            if (plus_or_minus[2] == 1){
                answer_3_random = answers_4_list[2] + values_c
            }else if(plus_or_minus[2] == 2){
                answer_3_random = answers_4_list[2] - values_c
            }

            if (abcd[0] == 1) {
                answer_a.text = "$values_c"
                answer_b.text = "$answer_1_random"
                answer_c.text = "$answer_2_random"
                answer_d.text = "$answer_3_random"
            } else if (abcd[0] == 2) {
                answer_a.text = "$answer_1_random"
                answer_b.text = "$values_c"
                answer_c.text = "$answer_2_random"
                answer_d.text = "$answer_3_random"
            } else if (abcd[0] == 3) {
                answer_a.text = "$answer_1_random"
                answer_b.text = "$answer_2_random"
                answer_c.text = "$values_c"
                answer_d.text = "$answer_3_random"
            } else if (abcd[0] == 4) {
                answer_a.text = "$answer_1_random"
                answer_b.text = "$answer_2_random"
                answer_c.text = "$answer_3_random"
                answer_d.text = "$values_c"
            }

            if (plus_or_minus[3] == 1){
                example_text.text = "$resula_a + $result_b ="
            }else if(plus_or_minus[3] == 2){
                example_text.text = "$resula_a - $result_b ="
            }

            level_text.text = "Lv.$level"
        }

        if (level in 4..10) {
            val from: Int = 10
            val until: Int = 21

            val from_btn = 1
            val until_btn = 10

            val plus_or_minus = List(4) { Random.nextInt(1, 3) }

            val values_a = List(2) { Random.nextInt(from, until) }
            val resula_a = values_a[0]
            val result_b = values_a[1]
            var values_c = resula_a + result_b

            val answers_4 = mutableSetOf<Int>()
            while (answers_4.size < 3) {
                answers_4.add(Random.nextInt(from_btn, until_btn))
            }
            val answers_4_list = answers_4.toList()
            var answer_1_random = answers_4_list[0] + values_c
            var answer_2_random = answers_4_list[1] + values_c
            var answer_3_random = answers_4_list[2] + values_c

            if (plus_or_minus[3] == 1){
                values_c = resula_a + result_b
            }else if(plus_or_minus[3] == 2){
                values_c = resula_a - result_b
            }

            if (plus_or_minus[0] == 1){
                answer_1_random = answers_4_list[0] + values_c
            }else if(plus_or_minus[0] == 2){
                answer_1_random = answers_4_list[0] - values_c
            }

            if (plus_or_minus[1] == 1){
                answer_2_random = answers_4_list[1] + values_c
            }else if(plus_or_minus[1] == 2){
                answer_2_random = answers_4_list[1] - values_c
            }

            if (plus_or_minus[2] == 1){
                answer_3_random = answers_4_list[2] + values_c
            }else if(plus_or_minus[2] == 2){
                answer_3_random = answers_4_list[2] - values_c
            }

            if (abcd[0] == 1) {
                answer_a.text = "$values_c"
                answer_b.text = "$answer_1_random"
                answer_c.text = "$answer_2_random"
                answer_d.text = "$answer_3_random"
            } else if (abcd[0] == 2) {
                answer_a.text = "$answer_1_random"
                answer_b.text = "$values_c"
                answer_c.text = "$answer_2_random"
                answer_d.text = "$answer_3_random"
            } else if (abcd[0] == 3) {
                answer_a.text = "$answer_1_random"
                answer_b.text = "$answer_2_random"
                answer_c.text = "$values_c"
                answer_d.text = "$answer_3_random"
            } else if (abcd[0] == 4) {
                answer_a.text = "$answer_1_random"
                answer_b.text = "$answer_2_random"
                answer_c.text = "$answer_3_random"
                answer_d.text = "$values_c"
            }

            if (plus_or_minus[3] == 1){
                example_text.text = "$resula_a + $result_b ="
            }else if(plus_or_minus[3] == 2){
                example_text.text = "$resula_a - $result_b ="
            }

            level_text.text = "Lv.$level"
        }
        if (level in 11..20) {
            val from: Int = 20
            val until: Int = 41

            val from_btn = 1
            val until_btn = 10

            val plus_or_minus = List(4) { Random.nextInt(1, 3) }

            val values_a = List(2) { Random.nextInt(from, until) }
            val resula_a = values_a[0]
            val result_b = values_a[1]
            var values_c = resula_a + result_b

            val answers_4 = mutableSetOf<Int>()
            while (answers_4.size < 3) {
                answers_4.add(Random.nextInt(from_btn, until_btn))
            }
            val answers_4_list = answers_4.toList()
            var answer_1_random = answers_4_list[0] + values_c
            var answer_2_random = answers_4_list[1] + values_c
            var answer_3_random = answers_4_list[2] + values_c

            if (plus_or_minus[3] == 1){
                values_c = resula_a + result_b
            }else if(plus_or_minus[3] == 2){
                values_c = resula_a - result_b
            }

            if (plus_or_minus[0] == 1){
                answer_1_random = answers_4_list[0] + values_c
            }else if(plus_or_minus[0] == 2){
                answer_1_random = answers_4_list[0] - values_c
            }

            if (plus_or_minus[1] == 1){
                answer_2_random = answers_4_list[1] + values_c
            }else if(plus_or_minus[1] == 2){
                answer_2_random = answers_4_list[1] - values_c
            }

            if (plus_or_minus[2] == 1){
                answer_3_random = answers_4_list[2] + values_c
            }else if(plus_or_minus[2] == 2){
                answer_3_random = answers_4_list[2] - values_c
            }

            if (abcd[0] == 1) {
                answer_a.text = "$values_c"
                answer_b.text = "$answer_1_random"
                answer_c.text = "$answer_2_random"
                answer_d.text = "$answer_3_random"
            } else if (abcd[0] == 2) {
                answer_a.text = "$answer_1_random"
                answer_b.text = "$values_c"
                answer_c.text = "$answer_2_random"
                answer_d.text = "$answer_3_random"
            } else if (abcd[0] == 3) {
                answer_a.text = "$answer_1_random"
                answer_b.text = "$answer_2_random"
                answer_c.text = "$values_c"
                answer_d.text = "$answer_3_random"
            } else if (abcd[0] == 4) {
                answer_a.text = "$answer_1_random"
                answer_b.text = "$answer_2_random"
                answer_c.text = "$answer_3_random"
                answer_d.text = "$values_c"
            }

            if (plus_or_minus[3] == 1){
                example_text.text = "$resula_a + $result_b ="
            }else if(plus_or_minus[3] == 2){
                example_text.text = "$resula_a - $result_b ="
            }

            level_text.text = "Lv.$level"
        }
        if (level in 21..40) {
            val from: Int = 40
            val until: Int = 81

            val from_btn = 1
            val until_btn = 30

            val plus_or_minus = List(4) { Random.nextInt(1, 3) }

            val values_a = List(2) { Random.nextInt(from, until) }
            val resula_a = values_a[0]
            val result_b = values_a[1]
            var values_c = resula_a + result_b

            val answers_4 = mutableSetOf<Int>()
            while (answers_4.size < 3) {
                answers_4.add(Random.nextInt(from_btn, until_btn))
            }
            val answers_4_list = answers_4.toList()
            var answer_1_random = answers_4_list[0] + values_c
            var answer_2_random = answers_4_list[1] + values_c
            var answer_3_random = answers_4_list[2] + values_c

            if (plus_or_minus[3] == 1){
                values_c = resula_a + result_b
            }else if(plus_or_minus[3] == 2){
                values_c = resula_a - result_b
            }

            if (plus_or_minus[0] == 1){
                answer_1_random = answers_4_list[0] + values_c
            }else if(plus_or_minus[0] == 2){
                answer_1_random = answers_4_list[0] - values_c
            }

            if (plus_or_minus[1] == 1){
                answer_2_random = answers_4_list[1] + values_c
            }else if(plus_or_minus[1] == 2){
                answer_2_random = answers_4_list[1] - values_c
            }

            if (plus_or_minus[2] == 1){
                answer_3_random = answers_4_list[2] + values_c
            }else if(plus_or_minus[2] == 2){
                answer_3_random = answers_4_list[2] - values_c
            }

            if (abcd[0] == 1) {
                answer_a.text = "$values_c"
                answer_b.text = "$answer_1_random"
                answer_c.text = "$answer_2_random"
                answer_d.text = "$answer_3_random"
            } else if (abcd[0] == 2) {
                answer_a.text = "$answer_1_random"
                answer_b.text = "$values_c"
                answer_c.text = "$answer_2_random"
                answer_d.text = "$answer_3_random"
            } else if (abcd[0] == 3) {
                answer_a.text = "$answer_1_random"
                answer_b.text = "$answer_2_random"
                answer_c.text = "$values_c"
                answer_d.text = "$answer_3_random"
            } else if (abcd[0] == 4) {
                answer_a.text = "$answer_1_random"
                answer_b.text = "$answer_2_random"
                answer_c.text = "$answer_3_random"
                answer_d.text = "$values_c"
            }

            if (plus_or_minus[3] == 1){
                example_text.text = "$resula_a + $result_b ="
            }else if(plus_or_minus[3] == 2){
                example_text.text = "$resula_a - $result_b ="
            }

            level_text.text = "Lv.$level"
        }
        if (level in 41..50) {
            val from: Int = 40
            val until: Int = 81

            val from_btn = 1
            val until_btn = 30

            val plus_or_minus = List(4) { Random.nextInt(1, 3) }

            val values_a = List(2) { Random.nextInt(from, until) }
            val resula_a = values_a[0]
            val result_b = values_a[1]
            var values_c = resula_a + result_b

            val answers_4 = mutableSetOf<Int>()
            while (answers_4.size < 3) {
                answers_4.add(Random.nextInt(from_btn, until_btn))
            }
            val answers_4_list = answers_4.toList()
            var answer_1_random = answers_4_list[0] + values_c
            var answer_2_random = answers_4_list[1] + values_c
            var answer_3_random = answers_4_list[2] + values_c

            if (plus_or_minus[3] == 1){
                values_c = resula_a + result_b
            }else if(plus_or_minus[3] == 2){
                values_c = resula_a - result_b
            }

            if (plus_or_minus[0] == 1){
                answer_1_random = answers_4_list[0] + values_c
            }else if(plus_or_minus[0] == 2){
                answer_1_random = answers_4_list[0] - values_c
            }

            if (plus_or_minus[1] == 1){
                answer_2_random = answers_4_list[1] + values_c
            }else if(plus_or_minus[1] == 2){
                answer_2_random = answers_4_list[1] - values_c
            }

            if (plus_or_minus[2] == 1){
                answer_3_random = answers_4_list[2] + values_c
            }else if(plus_or_minus[2] == 2){
                answer_3_random = answers_4_list[2] - values_c
            }

            if (abcd[0] == 1) {
                answer_a.text = "$values_c"
                answer_b.text = "$answer_1_random"
                answer_c.text = "$answer_2_random"
                answer_d.text = "$answer_3_random"
            } else if (abcd[0] == 2) {
                answer_a.text = "$answer_1_random"
                answer_b.text = "$values_c"
                answer_c.text = "$answer_2_random"
                answer_d.text = "$answer_3_random"
            } else if (abcd[0] == 3) {
                answer_a.text = "$answer_1_random"
                answer_b.text = "$answer_2_random"
                answer_c.text = "$values_c"
                answer_d.text = "$answer_3_random"
            } else if (abcd[0] == 4) {
                answer_a.text = "$answer_1_random"
                answer_b.text = "$answer_2_random"
                answer_c.text = "$answer_3_random"
                answer_d.text = "$values_c"
            }

            if (plus_or_minus[3] == 1){
                example_text.text = "$resula_a + $result_b ="
            }else if(plus_or_minus[3] == 2){
                example_text.text = "$resula_a - $result_b ="
            }

            level_text.text = "Lv.$level"
        }
    }
}
