package com.example.myapplication

class Cards(val cardName: String = "", var cardValue: Int = 0, val cardImage: String, val amountDeck: Int = 4) {

}

public var allCards = listOf<Cards>( // All the cards in the deck
    Cards("One", 1, "null"),
    Cards("Two", 2, "null"),
    Cards("Three", 3, "null"),
    Cards("Four", 4, "null"),
    Cards("Five", 5, "null"),
    Cards("Six", 6, "null"),
    Cards("Seven", 7, "null"),
    Cards("Eight", 8, "null"),
    Cards("Nine", 9, "null"),
    Cards("Ten", 10, "null"),
    Cards("Ace", 10, "null"),
    Cards("Queen", 10, "null"),
    Cards("Jack", 10, "null"),
    Cards("King", 10, "null")
)