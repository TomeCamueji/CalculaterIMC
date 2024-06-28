package com.example.imccalculater

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.imccalculater.model.Calc
import java.text.DecimalFormat

class ResultadoIMC : AppCompatActivity() {
    private lateinit var classificationText: TextView
    private lateinit var btnVoltar: Button
    private lateinit var textResult: TextView

            @SuppressLint("MissingInflatedId", "SetTextI18n")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_resultado_imc)

        processarResultado()
        btnVoltar.setOnClickListener {
            val intent = Intent(this, CalculaterImc::class.java)
            startActivity(intent)
        }



    }


    private fun processarResultado() {
        classificationText = findViewById(R.id.classificationText)
        textResult = findViewById(R.id.text_result)
        btnVoltar = findViewById(R.id.btnVoltar)


        val imc = intent.getDoubleExtra("IMC", 0.0)
        val formatador = DecimalFormat("#0.0")
        textResult.text = "Seu imc é: ${formatador.format(imc)}kg/m2"

        when {
            imc <= 18.5 -> {
                classificationText.setText(R.string.Magreza)
            }

            imc > 18.5 && imc <= 24.9 -> {
                classificationText.setText(R.string.Normal)
            }

            imc > 24.9 && imc <= 30 -> {
                classificationText.setText(R.string.Sobrepeso)
            }

            else -> {
                classificationText.setText(R.string.Obsid)
            }
        }

    }


}