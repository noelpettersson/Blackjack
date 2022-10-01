package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

fun drawCard() {

}

var playerCurrentCard: Int = 0 // Used for going through the players cards
var dealerCurrentCard: Int = 0
var yourTurn: Boolean = true // Player's turn?
var stand: Boolean = false

class PlayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

        val playerCardsArray: List<ImageView> = listOf(findViewById(R.id.card_1), findViewById(R.id.card_2), findViewById(R.id.card_3), findViewById(R.id.card_4), findViewById(R.id.card_5), findViewById(R.id.card_6), findViewById(R.id.card_7), findViewById(R.id.card_8), findViewById(R.id.card_9), findViewById(R.id.card_10), findViewById(R.id.card_11), findViewById(R.id.card_12))
        val dealerCardsArray: List<ImageView> = listOf(findViewById(R.id.dealerCard_1), findViewById(R.id.dealerCard_2), findViewById(R.id.dealerCard_3), findViewById(R.id.dealerCard_4), findViewById(R.id.dealerCard_5), findViewById(R.id.dealerCard_6), findViewById(R.id.dealerCard_7), findViewById(R.id.dealerCard_8), findViewById(R.id.dealerCard_9), findViewById(R.id.dealerCard_10), findViewById(R.id.dealerCard_11), findViewById(R.id.dealerCard_12))
        val hitButton = findViewById<Button>(R.id.test) // Hit
        val myScoreText = findViewById<TextView>(R.id.myScoreText)
        val restartButton = findViewById<Button>(R.id.restartButton)
        val standButton = findViewById<Button>(R.id.standButton)


        fun aceFunction() {
            val aceOneButton = findViewById<Button>(R.id.aceOneButton)
            val aceElevenButton = findViewById<Button>(R.id.aceElevenButton)

            aceOneButton.visibility = View.VISIBLE
            aceElevenButton.visibility = View.VISIBLE

            aceOneButton.setOnClickListener {
                myScore += 1
                myScoreText.text = myScore.toString()

                aceElevenButton.visibility = View.INVISIBLE
                aceOneButton.visibility = View.INVISIBLE
            }

            aceElevenButton.setOnClickListener {
                myScore += 11
                myScoreText.text = myScore.toString()

                aceElevenButton.visibility = View.INVISIBLE
                aceOneButton.visibility = View.INVISIBLE
            }
        }

        fun restartFunction() {
            restartButton.visibility = View.VISIBLE

            restartButton.setOnClickListener {
                val intent = Intent(this, PlayActivity::class.java)
                startActivity(intent)
                setContentView(R.layout.activity_main)
            }
        }

        fun conditions() {
            if (myScore > enemyScore && enemyScore >= 17 && stand) {
                Log.d("DEBUG", "User won")
                restartFunction()
            }

            else if(myScore < enemyScore && stand) {
                Log.d("DEBUG", "User lost")
                restartFunction()
            }

            if (myScore == 21 && enemyScore < 21) {
                Log.d("DEBUG", "Blackjack!")
                restartFunction()
            }

            if (myScore == 21 && enemyScore == 21) {
                Log.d("DEBUG", "Draw")
                restartFunction()
            }

            if (enemyScore > 21) {
                Log.d("DEBUG", "Dealer bust")
                restartFunction()
            }
        }

        fun drawCard() {
                val rand = Random(System.currentTimeMillis())
                val randomValue = rand.nextInt(12)


                if(allCards[randomValue].cardName == "Ace" && yourTurn) {
                    aceFunction()
                }

                if (yourTurn == true) {
                    playerCardsArray[playerCurrentCard].setImageResource(allCards[randomValue].cardImage)
                    playerCardsArray[playerCurrentCard].visibility = View.VISIBLE
                    myScore += allCards[randomValue].cardValue
                    playerCurrentCard++
                } else {
                    dealerCardsArray[dealerCurrentCard].setImageResource(allCards[randomValue].cardImage)
                    dealerCardsArray[dealerCurrentCard].visibility = View.VISIBLE
                    enemyScore += allCards[randomValue].cardValue
                    dealerCurrentCard++
                }


                myScoreText.text = myScore.toString()
            }


            fun initGame() {
                drawCard()
                drawCard()

                yourTurn = false
                drawCard()
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
