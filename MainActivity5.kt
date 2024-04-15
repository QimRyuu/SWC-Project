package com.example.carafirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity5 : AppCompatActivity() {

    private lateinit var BMI : Button
    private lateinit var Calories : Button
    private lateinit var LogOut : Button
    private lateinit var UsManual : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)

        BMI = findViewById(R.id.btnBMI)
        Calories = findViewById(R.id.btnCalory)
        LogOut = findViewById(R.id.btnLOut)
        UsManual = findViewById(R.id.btnUser)

        UsManual.setOnClickListener {
            val i = Intent(this, MainActivity7::class.java)
            startActivity(i)
        }

        BMI.setOnClickListener {
            val i = Intent(this, MainActivity3::class.java)
            startActivity(i)
        }

        LogOut.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }

        Calories.setOnClickListener {
            val i = Intent(this, MainActivity6::class.java)
            startActivity(i)
        }
    }
}