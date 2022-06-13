package com.example.challenge5.Splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
//import com.bumptech.glide.Glide
import com.example.challenge5.MainActivity
import com.example.challenge5.R


class SplashActivity : AppCompatActivity() {
    //var imgView = findViewById<ImageView>(R.id.img1)
    //var urlGambar = "https://i.ibb.co/HC5ZPgD/splash-screen1.png"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

     /*   Glide.with(this)
            .load(imgUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .centerCrop()
            .into(imgView)*/

        Handler(Looper.getMainLooper()).postDelayed( {
          val intentRadio = Intent(this@SplashActivity, MainActivity::class.java)

            startActivity(intentRadio)
        }, 1000)
    }
}


