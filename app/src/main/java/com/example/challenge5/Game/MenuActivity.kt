package com.example.challenge5.Game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.challenge5.R
import com.google.android.material.snackbar.Snackbar

class MenuActivity : AppCompatActivity() {

    lateinit var imgPemain : ImageView
    lateinit var imgCPU : ImageView
    lateinit var txtPemain : TextView
    lateinit var txtCPU : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        txtPemain = findViewById(R.id.lawan_pemain)
        txtCPU = findViewById(R.id.lawan_CPU)
        imgPemain = findViewById(R.id.img_lawan)
        imgCPU = findViewById(R.id.img_CPU)

        val layourRoot=findViewById<ConstraintLayout>(R.id.layout_menu)
        val namaUser = intent.getStringExtra("DATA_USER_NAME")




        txtPemain.setText("$namaUser VS Pemain")
        txtCPU.setText("$namaUser VS CPU")


        imgPemain.setOnClickListener{
            val intentToPemain = Intent(this, PemainSuit::class.java)
            intentToPemain.putExtra("DATA_USER_NAME",namaUser)
            startActivity(intentToPemain)
        }

        imgCPU.setOnClickListener{
            val intentToCPU = Intent(this, ComSuit::class.java)
            intentToCPU.putExtra("DATA_USER_NAME",namaUser)
            startActivity(intentToCPU)
        }



        Snackbar.make(layourRoot, "Selamat datang $namaUser", Snackbar.LENGTH_LONG).show()

    }


}