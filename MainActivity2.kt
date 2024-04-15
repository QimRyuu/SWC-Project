package com.example.carafirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity2 : AppCompatActivity() {

    private lateinit var dbRef: DatabaseReference
    private lateinit var submit: Button
    private lateinit var reset: Button
    private lateinit var name: EditText
    private lateinit var password: EditText
    private lateinit var phone: EditText
    private lateinit var email: EditText
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        submit = findViewById(R.id.btnSubmit)
        reset = findViewById(R.id.btnResetRegi)
        name = findViewById(R.id.eTName)
        password = findViewById(R.id.eTPassword)
        phone = findViewById(R.id.eTPhone)
        email = findViewById(R.id.eTEmail)
        btnBack = findViewById(R.id.btnBack)

        btnBack.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }

        submit.setOnClickListener {
            saveData(
                email.text.toString(), name.text.toString(),
                password.text.toString(), phone.text.toString()
            )
        }

        reset.setOnClickListener {
            name.setText("")
            password.setText("")
            phone.setText("")
            email.setText("")
        }
    }

    private fun saveData(e: String, n: String, p: String, t: String) {
        dbRef = FirebaseDatabase.getInstance().getReference("Customer")
        val customerId = dbRef.push().key!!

        // Assuming the constructor for Model class is (customerEmail, customerId, customerName, customerPhone)
        val em = Model(e, customerId, n, t)

        dbRef.child(customerId).setValue(em)
            .addOnSuccessListener {
                Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
                // Start the new activity after successful data insertion
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failure", Toast.LENGTH_LONG).show()
            }
    }

}