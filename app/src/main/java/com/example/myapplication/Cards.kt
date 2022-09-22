package com.example.myapplication

import android.graphics.drawable.Drawable

class Cards(val cardName: String = "", var cardValue: Int = 0, val cardImage: Int, val amountDeck: Int = 4) {

}

var loopTimes = 0

public var allCards = listOf<Cards>( // All the cards in the deck
    Cards("Two", 2, R.drawable.two_card),
    Cards("Three", 3, R.drawable.three_card),
    Cards("Four", 4, R.drawable.four_card),
    Cards("Five", 5, R.drawable.five_card),
    Cards("Six", 6, R.drawable.six_card),
    Cards("Seven", 7, R.drawable.seven_card),
    Cards("Eight", 8, R.drawable.eight_card),
    Cards("Nine", 9, R.drawable.nine_card),
    Cards("Ten", 10, R.drawable.ten_card),
    Cards("Ace", 0, R.drawable.ace_card),
    Cards("Queen", 10, R.drawable.queen_card),
    Cards("Jack", 10, R.drawable.jack_of_diamonds),
    Cards("King", 10, R.drawable.king_card)
)