package com.example.challenge5

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.challenge5.Game.ComSuit
import com.example.challenge5.Game.MenuActivity
import com.example.challenge5.Game.PemainSuit


class OnBoardImageLoginFragment : Fragment() {

    lateinit var etName: EditText
    //lateinit var imgLogin: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_on_boarding_login, container, false)

        etName = view.findViewById(R.id.edit_nama)

        etName.addTextChangedListener {
            listener?.afterUserInputName(it.toString())
        }

        // Inflate the layout for this fragment
        return view
    }

    var listener: UserNameInputListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is UserNameInputListener) {
            listener = context
        }
    }


    interface UserNameInputListener {
        fun afterUserInputName(input: String)
    }
}



