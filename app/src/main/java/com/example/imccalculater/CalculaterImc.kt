package com.example.imccalculater

import android.annotation.SuppressLint
import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.imccalculater.model.Calc

class CalculaterImc : AppCompatActivity() {
    private lateinit var EditWeight: EditText
    private lateinit var EditHeight: EditText
    private lateinit var btnCalc: Button
    private lateinit var textResult: TextView
    private lateinit var btnMenu: Button

    //    private lateinit var classification:TextView
    @SuppressLint(
        "StringFormatInvalid", "StringFormatMatches", "MissingInflatedId",
        "SuspiciousIndentation"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_calculater_imc)
        EditWeight = findViewById(R.id.editWeight)
        EditHeight = findViewById(R.id.editHeight)
        textResult = findViewById(R.id.text_result)
        btnMenu = findViewById(R.id.btn_menu)
//        classification = findViewById(R.id.classification)
        btnCalc = findViewById(R.id.btnCalc)
        val container = findViewById<LinearLayout>(R.id.Container)
        btnCalc.setOnClickListener {
            if (!validate()) {
                Toast.makeText(this, "Todos os campos devem ser preenchidos", Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }
            val weight = EditWeight.text.toString().toDouble()
            val height = EditHeight.text.toString().toDouble()
            val result = calc(weight, height)
            val imcResponsive = Classification(result)

        val intent = Intent(this@CalculaterImc,ResultadoIMC::class.java).apply {
            putExtra("IMC",result)
        }
            startActivity(intent)

//            val dialog = AlertDialog.Builder(this)
//                .setTitle(getString(R.string.res, result))
//                .setMessage((imcResponsive))
//                .setPositiveButton(android.R.string.ok) { _, which ->
//
//                }
//                .setNegativeButton(R.string.salved) { _, _ ->
//
//                    Thread {
//                        val app = application as App
//                        val dao = app.db.calcDao()
//                        dao.insert(Calc(type = "IMC", res = result))
//
//                        runOnUiThread {
//                            openListActivity()
//                        }
//                    }.start()
//
//
//                }
//                .create()
//                .show()

//            textResult.text = result.toString()

            btnMenu.setOnClickListener {
                Thread {
                    val app = application as App
                    val dao = app.db.calcDao()
                    dao.insert(Calc(type = "IMC", res = result))

                    runOnUiThread {
                        openListActivity()
                    }
                }.start()

            }
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_search) {
            finish()
            openListActivity()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openListActivity() {
        val intent = Intent(this@CalculaterImc, RegistList::class.java)
        intent.putExtra("type", "IMC")
        startActivity(intent)
    }

    @StringRes
    private fun Classification(imc: Double): Int {
        return when {
            imc < 18.5 -> R.string.Magreza
            imc > 18.5 && imc <= 24.9 -> R.string.Normal
            imc > 24.9 && imc <= 30 -> R.string.Sobrepeso
            else -> R.string.Obsid
        }
    }

    private fun validate(): Boolean {
        return (EditHeight.text.toString().isNotEmpty()
                && EditWeight.text.toString().isNotEmpty()
                && !EditHeight.text.toString().startsWith("0")
                && !EditWeight.text.toString().startsWith("0"))
    }

    private fun calc(weight: Double, height: Double): Double {
        return weight / ((height / 100.0) * (height / 100.0))
    }

}