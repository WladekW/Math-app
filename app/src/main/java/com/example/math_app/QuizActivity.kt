package com.example.math_app

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlin.random.Random

@Suppress("DEPRECATION")
class QuizActivity : AppCompatActivity() {

    private lateinit var countdown_text: TextView
    private lateinit var level_text: TextView
    private lateinit var menu: Button
    private lateinit var example_text: TextView
    private lateinit var answer_a: Button
    private lateinit var answer_b: Button
    private lateinit var answer_c: Button
    private lateinit var answer_d: Button
    private lateinit var Heart1: ImageView
    private lateinit var Heart2: ImageView
    private lateinit var Heart3: ImageView
//    private lateinit var countDownTimer: CountDownTimer

    private var level = 1
    private var hp_count = 3
    private var firstL = true
    private var abcd = List(1) { Random.nextInt(1, 5) }

    private var countDownTimer: CountDownTimer = object : CountDownTimer(6000, 1000) {
        @SuppressLint("SetTextI18n")
        override fun onTick(millisUntilFinished: Long) {
            countdown_text.text = "${millisUntilFinished / 1000}s"
        }

        @SuppressLint("SetTextI18n")
        override fun onFinish() {
            if (!firstL) {
                hp_count -= 1
                updateHp()
                if (hp_count <= 0){
                    backToMenu()
                }
            }
        }
    }

    @SuppressLint("MissingInflatedId", "SetTextI18n", "CutPasteId")
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
        Heart1 = findViewById(R.id.heart1)
        Heart2 = findViewById(R.id.heart2)
        Heart3 = findViewById(R.id.heart3)

        menu.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }

        if (firstL) {
            example_text.text = "This window will show simple math examples. Below are four answers, of which only one is correct."
            example_text.textSize = 16F
            example_text.height = 130
        }

        if (abcd[0] == 1 && firstL) {
            answer_a.text = "I'm understad"
        }
        if (abcd[0] == 2 && firstL) {
            answer_b.text = "I'm understad"
        }
        if (abcd[0] == 3 && firstL) {
            answer_c.text = "I'm understad"
        }
        if (abcd[0] == 4 && firstL) {
            answer_d.text = "I'm understad"
        }

        answer_a.setOnClickListener {
            if (abcd[0] == 1 && !firstL) {
                level += 1
            } else if (abcd[0] != 1 && !firstL) {
                hp_count -= 1
            }
            buttonList()
        }
        answer_b.setOnClickListener {
            if (abcd[0] == 2 && !firstL) {
                level += 1
            } else if (abcd[0] != 2 && !firstL) {
                hp_count -= 1
            }
            buttonList()
        }
        answer_c.setOnClickListener {
            if (abcd[0] == 3 && !firstL) {
                level += 1
            } else if (abcd[0] != 3 && !firstL) {
                hp_count -= 1
            }
            buttonList()
        }
        answer_d.setOnClickListener {
            if (abcd[0] == 4 && !firstL) {
                level += 1
            } else if (abcd[0] != 4 && !firstL) {
                hp_count -= 1
            }
            buttonList()
        }

    }
    private fun backToMenu(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }
    private fun buttonList(){
        if (hp_count <= 0){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
        nextLevel()
        updateHp()
        restartTimer()
        firstL = false
        example_text.textSize = 45F
        example_text.height = 100
        level_text.text = "Lv.$level"
    }
    private fun cDownStart() {
        countDownTimer = object : CountDownTimer(6000, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                countdown_text.text = "${millisUntilFinished / 1000}s"
            }

            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                if (!firstL) {
                    hp_count -= 1
                    updateHp()
                    if (hp_count <= 0){
                        backToMenu()
                    }
                }
            }
        }.start()

    }
    private fun restartTimer() {
        countDownTimer.cancel()
        cDownStart()
    }
    @SuppressLint("SetTextI18n")
    private fun updateHp() {
        when (hp_count) {
            2 -> {
                Heart3.visibility = View.INVISIBLE
            }
            1 -> {
                Heart2.visibility = View.INVISIBLE
            }
            0 -> {
                Heart1.visibility = View.INVISIBLE
            }
        }
        if (hp_count <= 0){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
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
            val until_btn = 20

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
            val until: Int = 101

            val from_btn = 1
            val until_btn = 20

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
        if (level in 51..60) {
            val from: Int = 100
            val until: Int = 301

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
        if (level in 61..80) {
            val from: Int = 300
            val until: Int = 701

            val from_btn = 1
            val until_btn = 50

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
        if (level in 81..100) {
            val from: Int = 700
            val until: Int = 1501

            val from_btn = 1
            val until_btn = 120

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
        if (level in 101..150) {
            val from: Int = 1500
            val until: Int = 3501

            val from_btn = 1
            val until_btn = 200

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
        if (level in 151..200) {
            val from: Int = 3501
            val until: Int = 6501

            val from_btn = 1
            val until_btn = 500

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
