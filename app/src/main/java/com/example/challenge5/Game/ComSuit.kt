package com.example.challenge5.Game

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.challenge5.MainActivity
import com.example.challenge5.R

class ComSuit : AppCompatActivity() {
    lateinit var batuPemain : ImageView
    lateinit var guntingPemain : ImageView
    lateinit var kertasPemain: ImageView
    lateinit var batuCom: ImageView
    lateinit var guntingCom: ImageView
    lateinit var kertasCom: ImageView
    lateinit var hasilSuit: TextView
    lateinit var ulang : ImageView
    lateinit var  imgClose : ImageView
    lateinit var namaPemain : TextView
    var hasil = 1
    var pilihSuit = 0
    var pilihCom = (1 until 4).random()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_com_suit)

        batuPemain = findViewById(R.id.batu1)
        guntingPemain = findViewById(R.id.gunting1)
        kertasPemain = findViewById(R.id.kertas1)
        batuCom = findViewById(R.id.batu2)
        guntingCom = findViewById(R.id.gunting2)
        kertasCom = findViewById(R.id.kertas2)
        hasilSuit = findViewById(R.id.hasil)
        ulang = findViewById(R.id.refresh)
        imgClose = findViewById(R.id.img_close)
        namaPemain = findViewById(R.id.namaPemain)

        imgClose.setOnClickListener{
            finish()
        }

        val namaUser = intent.getStringExtra("DATA_USER_NAME")
        namaPemain.setText("$namaUser")

        batuPemain.setOnClickListener{
            Log.d(MainActivity::class.java.simpleName,  "Pemain 1 memilih batu")
            selectSuit(batuPemain, pilihan = 1)
            batuPemain.setBackgroundColor(Color.parseColor("#FF709EB3"))
        }

        guntingPemain.setOnClickListener{
            Log.d(MainActivity::class.java.simpleName,  "Pemain 1 memilih gunting")
            selectSuit(guntingPemain, pilihan = 2)
            guntingPemain.setBackgroundColor(Color.parseColor("#FF709EB3"))
        }

        kertasPemain.setOnClickListener{
            Log.d(MainActivity::class.java.simpleName,  "Pemain 1 memilih kertas")
            selectSuit(kertasPemain, pilihan = 3)
            kertasPemain.setBackgroundColor(Color.parseColor("#FF709EB3"))
        }

        ulang.setOnClickListener{
            hapusPemain()
            hapusCom()
            reset()
        }



    }

    private fun closeDialog(){
        hapusPemain()
        hapusCom()
        reset()
    }

    // mengembalikan hasil suit textview ke awal
    private fun reset(){
        hasilSuit.setTextColor(Color.parseColor("#F62415"))
        hasilSuit.setText("VS")
        hasilSuit.setBackgroundColor(0)
        hasilSuit.setTextSize(40F)
    }

    // menampilkan pilihan hasil acak com
    private fun com(){
        if(pilihSuit>0) {
            if (pilihCom == 1){
                Log.d(MainActivity::class.java.simpleName, "Com memilih batu")
                batuCom.setBackgroundColor(Color.parseColor("#FF709EB3"))
                Toast.makeText(this,"CPU memilih batu", Toast.LENGTH_SHORT).show()
            }
            if (pilihCom == 2){
                Log.d(MainActivity::class.java.simpleName, "Com memilih gunting")
                guntingCom.setBackgroundColor(Color.parseColor("#FF709EB3"))
                Toast.makeText(this,"CPU memilih gunting", Toast.LENGTH_SHORT).show()
            }
            if (pilihCom == 3){
                Log.d(MainActivity::class.java.simpleName, "Com memilih kertas")
                kertasCom.setBackgroundColor(Color.parseColor("#FF709EB3"))
                Toast.makeText(this,"CPU memilih kertas", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun selectSuit(view: ImageView, pilihan:Int){
        pilihSuit = pilihan
        hapusCom()
        com()
        suit()
        hapusPemain()
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
        if((pilihSuit == 1 && pilihCom == 1)||(pilihSuit==2 && pilihCom==2) || (pilihSuit == 3 && pilihCom == 3)){
            Log.d(MainActivity::class.java.simpleName, "DRAW")
            hasil = 1
            hasilSuit.setBackgroundColor(Color.parseColor("#FF3F51B5"))
            hasilSuit.setTextColor(Color.parseColor("#FFE9EFE9"))
            hasilSuit.setTextSize(40F)
            hasilSuit.setText("DRAW")
            showDialog()
        }
        if((pilihSuit == 1 && pilihCom == 2)||(pilihSuit==2 && pilihCom==3) || (pilihSuit == 3 && pilihCom == 1)){
            Log.d(MainActivity::class.java.simpleName, "Pemain 1 Menang !!!")
            hasil = 2
            ubahText()
            hasilSuit.setText("Pemain 1 MENANG")
            showDialog()
        }
        if((pilihSuit == 1 && pilihCom == 3)||(pilihSuit==2 && pilihCom==1) || (pilihSuit == 3 && pilihCom == 2)){
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

    private fun hapusCom(){
        batuCom.setBackgroundColor(0)
        guntingCom.setBackgroundColor(0)
        kertasCom.setBackgroundColor(0)
        pilihCom = (1 until 4).random()
    }
}