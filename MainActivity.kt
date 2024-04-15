package com.example.carafirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    private lateinit var signup: Button
    private lateinit var login: Button
    private lateinit var about : Button
    private lateinit var email: EditText
    private lateinit var password: EditText
    // Declare the Firebase
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        signup = findViewById(R.id.btnSignUpp)
        login = findViewById(R.id.btnLoginn)
        email = findViewById(R.id.eTEmailLoginn)
        password = findViewById(R.id.eTPassLoginn)
        about = findViewById(R.id.btnAbout)

        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("Customer")

        signup.setOnClickListener {
            val i = Intent(this, MainActivity2::class.java)
            startActivity(i)
        }

        about.setOnClickListener {
            val i = Intent(this, MainActivity4::class.java)
            startActivity(i)
        }

        login.setOnClickListener {
            val emailText = email.text.toString()
            val passwordText = password.text.toString()

            if(emailText.isNotEmpty() && passwordText.isNotEmpty() )
            {
                login(emailText, passwordText)
            } else {
                Toast.makeText(this@MainActivity,
                    "All fields are mandatory", Toast.LENGTH_LONG ).show()
            }
        }

    }

    private fun login(email:String, password:String)
    {
        databaseReference.orderByChild("customerEmail").equalTo(email).addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()){
                    for (custSnapshot in dataSnapshot.children){
                        val model = custSnapshot.getValue(Model::class.java)

                        if (model != null && model.customerPassword==password){
                            Toast.makeText(this@MainActivity, "Login Successful", Toast.LENGTH_LONG).show()
                            startActivity(Intent(this@MainActivity, MainActivity5::class.java))
                            finish()
                            return
                        }
                    }
                }

                Toast.makeText(this@MainActivity, "Login Failed",Toast.LENGTH_LONG).show()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@MainActivity, "Database Error: ${databaseError.message}", Toast.LENGTH_LONG).show()
            }
        } )
    }
}