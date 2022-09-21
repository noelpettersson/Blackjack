package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

var moneyInBank: Int = 500 // The amount of money the player currently have
var bet: Int = 0 // Variable used for the amount of money in the bet


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val moneyLeftText = findViewById<TextView>(R.id.moneyLeftText)
        val betAmountText = findViewById<TextView>(R.id.betAmountText)

        moneyLeftText.text = "Money in bank: ${moneyLeftText}"
        betAmountText.text = "Bet: ${betAmountText}"
    }
}