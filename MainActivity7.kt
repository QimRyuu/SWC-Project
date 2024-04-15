package com.example.carafirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity7 : AppCompatActivity() {

    private lateinit var bback : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main7)

        bback = findViewById(R.id.btnBack3)

        bback.setOnClickListener {
            val i = Intent(this, MainActivity5::class.java)
            startActivity(i)
        }

    }
}