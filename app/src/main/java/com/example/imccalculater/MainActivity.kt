package com.example.imccalculater

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var btnAcess:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        btnAcess = findViewById(R.id.btn_acess)

        btnAcess.setOnClickListener {
            val intent = Intent(this@MainActivity,CalculaterImc::class.java)
            startActivity(intent)
        }

    }
}