package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

var loopedTimes: Int = 0

class PlayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

        val cardsArray: List<ImageView> = listOf(findViewById(R.id.card_1), findViewById(R.id.card_2))
        val test = findViewById<Button>(R.id.button)
        val myScoreText = findViewById<TextView>(R.id.myScoreText)

        fun drawCard(amount: Int) {
            while(loopedTimes != amount) {
                val rand = Random(System.currentTimeMillis())
                val randomValue = rand.nextInt(12)

                cardsArray[loopedTimes].setImageResource(allCards[randomValue].cardImage)
                myScore += allCards[randomValue].cardValue

                loopedTimes++
            }
        }


            println("HEEEEEEEEEEEEEEEEEEJ!!!!!!!!!!!!")
            drawCard(2)
            myScoreText.text = myScore.toString()



        }
    }
