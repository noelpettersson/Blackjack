package com.example.myapplication

import android.content.Intent
import android.media.MediaPlayer
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

var playMusic: Boolean = true

var moneyInBank: Int = 500 // The amount of money the player currently have
var bet: Int = 0 // Variable used for the amount of money in the bet

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mediaPlayer = MediaPlayer.create(this, R.raw.bgmusic)
        val speakerButton = findViewById<ImageView>(R.id.speakerButton)

        val moneyLeftText = findViewById<TextView>(R.id.moneyLeftText)
        val betAmountText = findViewById<TextView>(R.id.betAmountText)
        val errorMessageText = findViewById<TextView>(R.id.errorMessage)

        val playButton = findViewById<Button>(R.id.playButton)

        val bet10Button = findViewById<Button>(R.id.bet10)
        val bet50Button = findViewById<Button>(R.id.bet50)
        val bet100Button = findViewById<Button>(R.id.bet100)

        mediaPlayer.start()

        speakerButton.setOnClickListener() {
            if (playMusic) {
                playMusic = false
                mediaPlayer.stop()
                speakerButton.setImageResource(R.drawable.speaker_on)
            } else {
                playMusic = true
                mediaPlayer.start()
                speakerButton.setImageResource(R.drawable.speakeroff)
            }


            Log.d("DEBUG", "Speaker button pressed")
        }

        playButton.setOnClickListener() {
            if (bet > 0) { // Only allow user to play if bet is above 0
                val Intent = Intent(this, PlayActivity::class.java)
                startActivity(Intent) // Switch to PlayActivity
            } else {
                Log.d("DEBUG", "No bet set")
                errorMessageText.visibility = View.VISIBLE
                errorMessageText.text = "${getString(R.string.error_bet)}"
            }
        }

        //
        fun betSetter (betAmount: Int) { // Set bet amount
           if(moneyInBank > 0) { // If user has no money do nothing
               bet += betAmount // Update bet amount
               moneyInBank -= betAmount // Update money in bank
               moneyLeftText.text = "${getString(R.string.money_left)} ${moneyInBank}" // Update text
               betAmountText.text = "${getString(R.string.bet_amount)} ${bet}"
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


    }
    override fun onResume() {
        super.onResume()
        Log.d("DEBUG", "Activity resumed")

        //Update money left and bet text when activity loads after playActivity
        val moneyLeftText = findViewById<TextView>(R.id.moneyLeftText)
        val betAmountText = findViewById<TextView>(R.id.betAmountText)
        val errorMessageText = findViewById<TextView>(R.id.errorMessage)

        errorMessageText.visibility = View.INVISIBLE

        moneyLeftText.text = "${getString(R.string.money_left)} ${moneyInBank}"
        betAmountText.text = "${getString(R.string.bet_amount)} ${bet}"
    }
}
