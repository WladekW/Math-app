package com.example.math_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val start: Button = findViewById(R.id.start)

        start.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
            this.overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_left);
        }

    }
}