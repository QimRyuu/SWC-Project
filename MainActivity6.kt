package com.example.carafirebase

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity6 : AppCompatActivity() {

    private lateinit var weightEditText: EditText
    private lateinit var heightEditText: EditText
    private lateinit var ageEditText: EditText
    private lateinit var maleButton: Button
    private lateinit var femaleButton: Button
    private lateinit var calculateButton: Button
    private lateinit var back: Button
    private lateinit var reset2: Button
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main6)

        weightEditText = findViewById(R.id.weightEditText)
        heightEditText = findViewById(R.id.heightEditText)
        ageEditText = findViewById(R.id.ageEditText)
        maleButton = findViewById(R.id.maleButton)
        femaleButton = findViewById(R.id.femaleButton)
        back = findViewById(R.id.btnBack2)
        reset2 = findViewById(R.id.btnReset2)
        calculateButton = findViewById(R.id.calculateButton)
        resultTextView = findViewById(R.id.resultTextView)

        maleButton.setOnClickListener {
            maleButton.isSelected = true
            femaleButton.isSelected = false
        }

        femaleButton.setOnClickListener {
            femaleButton.isSelected = true
            maleButton.isSelected = false
        }

        calculateButton.setOnClickListener {
            calculateCalories()
        }

        back.setOnClickListener {
            val i = Intent(this, MainActivity5::class.java)
            startActivity(i)
        }

        reset2.setOnClickListener {
            weightEditText.setText("")
            heightEditText.setText("")
            ageEditText.setText("")
            resultTextView.setText("")
        }
    }

    private fun calculateCalories() {
        val weight = weightEditText.text.toString().toDouble()
        val height = heightEditText.text.toString().toDouble()
        val age = ageEditText.text.toString().toInt()

        val bmr: Double = if (maleButton.isSelected) {
            calculateMaleBMR(weight, height, age)
        } else {
            calculateFemaleBMR(weight, height, age)
        }

        resultTextView.text = "Your Basal Metabolic Rate (BMR) is $bmr calories per day."
    }

    private fun calculateMaleBMR(weight: Double, height: Double, age: Int): Double {
        return 10 * weight + 6.25 * height - 5 * age + 5
    }

    private fun calculateFemaleBMR(weight: Double, height: Double, age: Int): Double {
        return 10 * weight + 6.25 * height - 5 * age - 161
    }
}