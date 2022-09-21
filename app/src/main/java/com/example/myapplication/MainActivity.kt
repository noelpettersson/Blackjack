package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

var moneyInBank: Int = 500 // The amount of money the player currently have
var bet: Int = 0 // Variable used for the amount of money in the bet
var yourTurn: Boolean = true
var myScore: Int = 0
var enemyScore: Int = 0


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val moneyLeftText = findViewById<TextView>(R.id.moneyLeftText)
        val betAmountText = findViewById<TextView>(R.id.betAmountText)

        val playButton = findViewById<Button>(R.id.playButton)

        val bet10Button = findViewById<Button>(R.id.bet10)
        val bet50Button = findViewById<Button>(R.id.bet50)
        val bet100Button = findViewById<Button>(R.id.bet100)

        moneyLeftText.text = "Money in bank: ${moneyInBank}"
        betAmountText.text = "Bet: ${bet}"

        playButton.setOnClickListener() {
            setContentView(R.layout.activity_play)
        }

        fun betSetter(betAmount: Int) {
           if(moneyInBank > 0) {
               bet += betAmount
               moneyInBank -= betAmount
               moneyLeftText.text = "Money in bank: ${moneyInBank}"
               betAmountText.text = "Bet: ${bet}"
           }
        }

        bet10Button.setOnClickListener {
            betSetter(10)
        }

        bet50Button.setOnClickListener {
            betSetter(50)
        }

        bet100Button.setOnClickListener {
            betSetter(100)
        }



    }


}