package com.example.challenge5

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.challenge5.Fragment.OnBoardImageFragment
import com.example.challenge5.Fragment.OnBoardImageTwoFragment
import com.example.challenge5.Game.ComSuit
import com.example.challenge5.Game.MenuActivity
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator

class MainActivity : AppCompatActivity(), OnBoardImageLoginFragment.UserNameInputListener {

    lateinit var viewPager : ViewPager
    lateinit var springDotsIndicator : SpringDotsIndicator
    lateinit var imgNext : ImageView
    var namaUser = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.viewPager)
        springDotsIndicator = findViewById(R.id.spring_dots_indicator)
        imgNext = findViewById(R.id.img_next)

        viewPager.adapter = SimpleViewPagerAdapter(supportFragmentManager)
        springDotsIndicator.setViewPager(viewPager)

        imgNext.setOnClickListener{
            val currentIndex = viewPager.currentItem
            viewPager.currentItem = currentIndex+1

            if(currentIndex == 2 ){
                val intentToMenu = Intent(this, MenuActivity::class.java)
                intentToMenu.putExtra("DATA_USER_NAME",namaUser)
                startActivity(intentToMenu)
            }

            if(currentIndex==0){
                listener?.onDataSend("data from activity")
            }
        }

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                if(position == 1 || position == 0 || namaUser.isNotEmpty()) imgNext.visibility = View.VISIBLE
                else imgNext.visibility = View.GONE

            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

        viewPager.setOnClickListener{
            listener?.onDataSend("data from activity")
        }
    }

    private inner class SimpleViewPagerAdapter(fm : FragmentManager):FragmentStatePagerAdapter(fm){
        override fun getCount(): Int = 3

        override fun getItem(position: Int): Fragment = when (position) {
            0 -> OnBoardImageFragment()
            1 -> OnBoardImageTwoFragment()
            else -> OnBoardImageLoginFragment()
        }
    }
    

    override fun afterUserInputName(input: String) {
        if(input.isNotEmpty())imgNext.visibility = View.VISIBLE else imgNext.visibility = View.GONE
        namaUser = input
    }

    fun setFragmentListener(listener:OnSendDataToFragment){
        this.listener = listener
    }

    var listener: OnSendDataToFragment? = null

    interface OnSendDataToFragment{
        fun onDataSend(input:String)
    }

}

