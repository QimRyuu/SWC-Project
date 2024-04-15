package com.example.carafirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.text.DecimalFormat

class MainActivity3 : AppCompatActivity() {

    //initialize all component
    private lateinit var btnR : Button
    private lateinit var btnCal : Button
    private lateinit var btnB : Button
    private lateinit var eTw : EditText
    private lateinit var eTh : EditText
    private lateinit var tVs : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        //reference the component
        btnR = findViewById<Button>(R.id.btnReset)
        btnCal = findViewById<Button>(R.id.btnCalculate)
        btnB = findViewById<Button>(R.id.btnBack1)
        eTw = findViewById<EditText>(R.id.eTweight)
        eTh = findViewById<EditText>(R.id.eTheight)
        tVs = findViewById<TextView>(R.id.tVStatus)

        //initialize function decimal
        val df = DecimalFormat("#,###,###.##")

        btnCal.setOnClickListener {
            //declare the variable
            val weight: Double = eTw.text.toString().toDouble()
            val height: Double = eTh.text.toString().toDouble()

            //calculate BMI
            //div - division
            val bmi = weight / (height * height)

            //display the result at TextView
            tVs.text = "Result BMI: " + df.format(bmi).toString()

            // Calculate Grade
            val grade = calculateGrade(bmi)
            tVs.text = grade
        }

        btnB.setOnClickListener {
            val i = Intent(this, MainActivity5::class.java)
            startActivity(i)
        }

        btnR.setOnClickListener {
            //to reset all records
            eTw.text.clear()
            eTh.text.clear()
            tVs.text = ""
        }
    }

    private fun calculateGrade(score: Double): String {
        return when {
            score >= 30 -> "Obesity"
            score >= 25 -> "Overweight"
            score >= 18.5 -> "Normal Weight"
            else -> "Underweight"
        }
    }
}
