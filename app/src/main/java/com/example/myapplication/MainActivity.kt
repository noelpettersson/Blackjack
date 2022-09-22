package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

var moneyInBank: Int = 500 // The amount of money the player currently have
var bet: Int = 0 // Variable used for the amount of money in the bet
var yourTurn: Boolean = true // Player's turn?
var myScore: Int = 0 // Player's points from cards
var enemyScore: Int = 0 // Dealers points from cards


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Init variables
        val moneyLeftText = findViewById<TextView>(R.id.moneyLeftText)
        val betAmountText = findViewById<TextView>(R.id.betAmountText)

        val playButton = findViewById<Button>(R.id.playButton)

        val bet10Button = findViewById<Button>(R.id.bet10)
        val bet50Button = findViewById<Button>(R.id.bet50)
        val bet100Button = findViewById<Button>(R.id.bet100)

        moneyLeftText.text = "Money in bank: ${moneyInBank}"
        betAmountText.text = "Bet: ${bet}"

        playButton.setOnClickListener() {
            val Intent = Intent(this, PlayActivity::class.java)
            startActivity(Intent) // Switch to PlayActivity
        }

        //
        fun betSetter(betAmount: Int) { // Set bet amount
           if(moneyInBank > 0) { // If user has no money do nothing
               bet += betAmount // Update bet amount
               moneyInBank -= betAmount // Update money in bank
               moneyLeftText.text = "Money in bank: ${moneyInBank}" // Update text
               betAmountText.text = "Bet: ${bet}"
           }
        }

        bet10Button.setOnClickListener { //Bet 10 chips button
            betSetter(10)
        }

        bet50Button.setOnClickListener { // Bet 50 chips button
            betSetter(50)
        }

        bet100Button.setOnClickListener { // Bet 100 chips button
            betSetter(100)
        }
        //


    }


}