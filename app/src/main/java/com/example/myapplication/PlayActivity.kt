package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

var playerCurrentCard: Int = 0 // Used for going through the players cards
var dealerCurrentCard: Int = 0
var yourTurn: Boolean = true // Player's turn?

class PlayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

        val playerCardsArray: List<ImageView> = listOf(findViewById(R.id.card_1), findViewById(R.id.card_2), findViewById(R.id.card_3), findViewById(R.id.card_4))
        val dealerCardsArray: List<ImageView> = listOf(findViewById(R.id.dealerCard_1), findViewById(R.id.dealerCard_2), findViewById(R.id.dealerCard_3), findViewById(R.id.dealerCard_4))
        val test = findViewById<Button>(R.id.test) // Hit
        val myScoreText = findViewById<TextView>(R.id.myScoreText)
        val restartButton = findViewById<Button>(R.id.restartButton)

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

        fun drawCard(amount: Int) {
            var loopedTimes: Int = 0

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
       // }


            fun initGame() {
                drawCard(2)
                drawCard(1)

                yourTurn = false

                drawCard(1)
                drawCard(1)

                yourTurn = true

                test.setOnClickListener {
                    drawCard(1)

                    if(myScore > 21) {
                        println("Bust")
                        restartButton.visibility = View.VISIBLE

                        restartButton.setOnClickListener {
                            val Intent = Intent(this, PlayActivity::class.java)
                            startActivity(Intent)
                            setContentView(R.layout.activity_main)
                        }
                    }
                }


            }

        initGame()


        }
    }
