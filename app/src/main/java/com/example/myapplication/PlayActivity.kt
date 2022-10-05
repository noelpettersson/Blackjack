package com.example.myapplication

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.recreate
import kotlinx.coroutines.delay
import java.util.*

//TODO Spara money variabeln lokalt på enheten
//TODO Bakgrund, musik, ljudeffekter


class PlayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

        var myScore: Int = 0 // Player's points from cards
        var enemyScore: Int = 0 // Dealers points from cards
        var playerCurrentCard: Int = 0 // Used for going through the players cards
        var dealerCurrentCard: Int = 0
        var yourTurn: Boolean = true // Player's turn?
        var stand: Boolean = false // Player stand boolean

        val playerCardsArray: List<ImageView> = listOf(findViewById(R.id.card_1), findViewById(R.id.card_2), findViewById(R.id.card_3), findViewById(R.id.card_4), findViewById(R.id.card_5), findViewById(R.id.card_6), findViewById(R.id.card_7), findViewById(R.id.card_8), findViewById(R.id.card_9), findViewById(R.id.card_10), findViewById(R.id.card_11), findViewById(R.id.card_12))
        val dealerCardsArray: List<ImageView> = listOf(findViewById(R.id.dealerCard_1), findViewById(R.id.dealerCard_2), findViewById(R.id.dealerCard_3), findViewById(R.id.dealerCard_4), findViewById(R.id.dealerCard_5), findViewById(R.id.dealerCard_6), findViewById(R.id.dealerCard_7), findViewById(R.id.dealerCard_8), findViewById(R.id.dealerCard_9), findViewById(R.id.dealerCard_10), findViewById(R.id.dealerCard_11), findViewById(R.id.dealerCard_12))

        val hitButton = findViewById<Button>(R.id.test)
        val restartButton = findViewById<Button>(R.id.restartButton)
        val standButton = findViewById<Button>(R.id.standButton)

        val myScoreText = findViewById<TextView>(R.id.myScoreText) // Players score
        val dealerScoreText = findViewById<TextView>(R.id.dealerScoreText)

        fun restartFunction() { // Function to restart the game
            restartButton.visibility = View.VISIBLE // Make restart button visible
            hitButton.visibility = View.INVISIBLE
            standButton.visibility = View.INVISIBLE

            restartButton.setOnClickListener {
                bet = 0
                super.finish()
            }
        }

        fun conditions() {
            if (myScore > enemyScore && enemyScore >= 17 && stand) {
                moneyInBank += bet * 2
                Log.d("DEBUG", "User won, money in bank: ${moneyInBank}")
                restartFunction()
            }

            if(myScore < enemyScore && stand && enemyScore < 22) {
                Log.d("DEBUG", "User lost, money in bank: ${moneyInBank}")
                bet = 0 // remove later
                restartFunction()
            }

            if (myScore == enemyScore && stand && enemyScore >= 17) {
                moneyInBank += bet
                Log.d("DEBUG", "Draw, money in bank: ${moneyInBank}")
                restartFunction()
            }

            if (enemyScore > 21) {
                moneyInBank += bet * 2
                Log.d("DEBUG", "Dealer bust, money in bank: ${moneyInBank}")
                restartFunction()
            }

            if (myScore > 21) {
                bet = 0 // remove later
                Log.d("DEBUG", "Player bust, money in bank: ${moneyInBank}")
                restartFunction()
            }
        }

        fun drawCard() {
                val rand = Random()
                val randomValue = rand.nextInt(12) // Create random value from 1-12 from current time seed


                if(allCards[randomValue].cardName == "Ace" && yourTurn && myScore <= 11) { // Players ace function
                    myScore += 11
                } else if(allCards[randomValue].cardName == "Ace" && yourTurn && myScore >= 11) {
                    myScore += 1
                }

                if(allCards[randomValue].cardName == "Ace" && !yourTurn && enemyScore <= 11) { // Dealer's ace function
                    enemyScore += 11
                } else if(allCards[randomValue].cardName == "Ace" && !yourTurn && enemyScore >= 11) {
                    enemyScore += 1
                }

                if (yourTurn == true) { // Draw player's card
                    playerCardsArray[playerCurrentCard].setImageResource(allCards[randomValue].cardImage) // Set cards image in array
                    playerCardsArray[playerCurrentCard].visibility = View.VISIBLE // Make card slot visible
                    myScore += allCards[randomValue].cardValue // Add the cards value to the player's score
                    playerCurrentCard++ // Move up in the array for card
                    myScoreText.text = myScore.toString()
                } else { // Dealers card
                    dealerCardsArray[dealerCurrentCard].setImageResource(allCards[randomValue].cardImage)
                    dealerCardsArray[dealerCurrentCard].visibility = View.VISIBLE
                    enemyScore += allCards[randomValue].cardValue
                    dealerCurrentCard++
                    dealerScoreText.text = enemyScore.toString()
                }

            }


            fun initGame() {
                drawCard()
                drawCard()

                yourTurn = false
                drawCard()

                hitButton.setOnClickListener {
                    yourTurn = true
                    drawCard()
                    conditions()
                }

                standButton.setOnClickListener {
                    Log.d("!!", "Stand button pressed")
                    stand = true
                    yourTurn = false

                    hitButton.visibility = View.INVISIBLE
                    standButton.visibility = View.INVISIBLE

                    while (enemyScore < 17) {
                        drawCard()
                    }

                    conditions()

                }


            }

        initGame()


        }
    }
