package com.example.challenge5.Game

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.challenge5.MainActivity
import com.example.challenge5.R

class PemainSuit : AppCompatActivity() {
    lateinit var batuPemain : ImageView
    lateinit var guntingPemain : ImageView
    lateinit var kertasPemain: ImageView
    lateinit var batuLawan : ImageView
    lateinit var guntingLawan : ImageView
    lateinit var kertasLawan: ImageView
    lateinit var hasilSuit: TextView
    lateinit var ulang : ImageView
    lateinit var  imgClose : ImageView
    lateinit var userGame : TextView
    var hasil = 1
    var pilihSuit = 0
    var pilihSuitLawan = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pemain_suit)

        batuPemain = findViewById(R.id.batu1)
        guntingPemain = findViewById(R.id.gunting1)
        kertasPemain = findViewById(R.id.kertas1)
        batuLawan = findViewById(R.id.batu2)
        guntingLawan = findViewById(R.id.gunting2)
        kertasLawan = findViewById(R.id.kertas2)
        hasilSuit = findViewById(R.id.hasil)
        ulang = findViewById(R.id.refresh)
        imgClose = findViewById(R.id.img_close)
        userGame = findViewById(R.id.user)


        val namaUser = intent.getStringExtra("DATA_USER_NAME")
        userGame.setText("$namaUser")


        imgClose.setOnClickListener{
            finish()
        }

        batuPemain.setOnClickListener{
            Log.d(MainActivity::class.java.simpleName,  "Pemain 1 memilih batu")
            pilihSuit = 1
            selectSuit()
            batuPemain.setBackgroundColor(Color.parseColor("#FF709EB3"))
            Toast.makeText(this,"Pemain 1 memilih batu",Toast.LENGTH_SHORT).show()
        }

        guntingPemain.setOnClickListener{
            Log.d(MainActivity::class.java.simpleName,  "Pemain 1 memilih gunting")
            pilihSuit = 2
            selectSuit()
            guntingPemain.setBackgroundColor(Color.parseColor("#FF709EB3"))
            Toast.makeText(this,"Pemain 1 memilih gunting",Toast.LENGTH_SHORT).show()
        }

        kertasPemain.setOnClickListener{
            Log.d(MainActivity::class.java.simpleName,  "Pemain 1 memilih kertas")
            pilihSuit = 3
            selectSuit()
            kertasPemain.setBackgroundColor(Color.parseColor("#FF709EB3"))
            Toast.makeText(this,"Pemain 1 memilih kertas",Toast.LENGTH_SHORT).show()
        }
        batuLawan.setOnClickListener{
            Log.d(MainActivity::class.java.simpleName,  "Pemain 2 memilih batu")
            pilihSuitLawan = 1
            selectSuit()
            batuLawan.setBackgroundColor(Color.parseColor("#FF709EB3"))
            Toast.makeText(this,"Pemain 2 memilih batu",Toast.LENGTH_SHORT).show()
        }

        guntingLawan.setOnClickListener{
            Log.d(MainActivity::class.java.simpleName,  "Pemain 2 memilih gunting")
            pilihSuitLawan = 2
            selectSuit()
            guntingLawan.setBackgroundColor(Color.parseColor("#FF709EB3"))
            Toast.makeText(this,"Pemain 2 memilih gunting",Toast.LENGTH_SHORT).show()
        }

        kertasLawan.setOnClickListener{
            Log.d(MainActivity::class.java.simpleName,  "Pemain 2 memilih kertas")
            pilihSuitLawan = 3
            selectSuit()
            kertasLawan.setBackgroundColor(Color.parseColor("#FF709EB3"))
            Toast.makeText(this,"Pemain 2 memilih kertas",Toast.LENGTH_SHORT).show()
        }

        ulang.setOnClickListener{
            closeDialog()
        }

    }

    private fun closeDialog(){
        hapusPemain()
        hapusLawan()
        reset()
        pilihSuit = 0
        pilihSuitLawan = 0
    }

    // mengembalikan hasil suit textview ke awal
    private fun reset(){
        hasilSuit.setTextColor(Color.parseColor("#F62415"))
        hasilSuit.setText("VS")
        hasilSuit.setBackgroundColor(0)
        hasilSuit.setTextSize(40F)
    }

    // menampilkan pilihan hasil acak com

    private fun selectSuit(){
        if(pilihSuitLawan==0){
        Handler(Looper.getMainLooper()).postDelayed({
            hapusPemain()
        },500)}
        hapusLawan()
        if(pilihSuitLawan>0){
            when(pilihSuit){
                1 -> batuPemain.setBackgroundColor(Color.parseColor("#FF709EB3"))
                2 -> guntingPemain.setBackgroundColor(Color.parseColor("#FF709EB3"))
                3 -> kertasPemain.setBackgroundColor(Color.parseColor("#FF709EB3"))
            }
        }
        suit()
        selectSuitLawan()
        hapusPemain()

    }

    private fun selectSuitLawan(){


    }

    private fun showDialog(){

        val dialogBuilder = AlertDialog.Builder(this)
        val viewCustome = LayoutInflater.from(this).inflate(R.layout.dialog_winner,null,false)

        dialogBuilder.setView(viewCustome)

        val dialog = dialogBuilder.create()

        val btnRefresh = viewCustome.findViewById<Button>(R.id.btn_refresh)
        val btnMenu = viewCustome.findViewById<Button>(R.id.btn_menu)

        val txtResult = viewCustome.findViewById<TextView>(R.id.txt_result)
        val namaUser = intent.getStringExtra("DATA_USER_NAME")

        when(hasil){
            1 -> txtResult.setText("SERI")
            2 -> txtResult.setText("$namaUser MENANG!")
            3 -> txtResult.setText("CPU MENANG!")
        }

        btnRefresh.setOnClickListener{
            closeDialog()
            dialog.dismiss()
        }
        btnMenu.setOnClickListener{
            finish()
        }

        dialog.show()

    }

    // algoritma suit
    private fun suit(){
        if((pilihSuit == 1 && pilihSuitLawan == 1)||(pilihSuit==2 && pilihSuitLawan==2) || (pilihSuit == 3 && pilihSuitLawan == 3)){
            Log.d(MainActivity::class.java.simpleName, "DRAW")
            hasil = 1
            hasilSuit.setBackgroundColor(Color.parseColor("#FF3F51B5"))
            hasilSuit.setTextColor(Color.parseColor("#FFE9EFE9"))
            hasilSuit.setTextSize(40F)
            hasilSuit.setText("DRAW")
            showDialog()
        }
        if((pilihSuit == 1 && pilihSuitLawan == 2)||(pilihSuit==2 && pilihSuitLawan==3) || (pilihSuit == 3 && pilihSuitLawan == 1)){
            Log.d(MainActivity::class.java.simpleName, "Pemain 1 Menang !!!")
            hasil = 2
            ubahText()
            hasilSuit.setText("Pemain 1 MENANG")
            showDialog()
        }
        if((pilihSuit == 1 && pilihSuitLawan == 3)||(pilihSuit==2 && pilihSuitLawan==1) || (pilihSuit == 3 && pilihSuitLawan == 2)){
            Log.d(MainActivity::class.java.simpleName, "Pemain 2 Menang !!!")
            hasil = 3
            ubahText()
            hasilSuit.setText("Pemain 2 MENANG")
            showDialog()
        }
    }

    private fun ubahText(){
        hasilSuit.setBackgroundColor(Color.parseColor("#FF4CAF50"))
        hasilSuit.setTextColor(Color.parseColor("#FFE9EFE9"))
        hasilSuit.setTextSize(15F)
    }

    private fun hapusPemain(){
        batuPemain.setBackgroundColor(0)
        guntingPemain.setBackgroundColor(0)
        kertasPemain.setBackgroundColor(0)
    }

    private fun hapusLawan(){
        batuLawan.setBackgroundColor(0)
        guntingLawan.setBackgroundColor(0)
        kertasLawan.setBackgroundColor(0)
    }
}