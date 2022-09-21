package com.example.myapplication

class Cards(val cardName: String = "", var cardValue: Int = 0, val cardImage: String, val amountDeck: Int = 4) {

}

var loopTimes = 0

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

fun drawCard(amount: Int) {
    while (loopTimes != amount) {
        val randomValue = (1..13).random()
        val currentName: String = allCards[randomValue].cardName
        var currentValue: Int = allCards[randomValue].cardValue

        println("You drew: ${currentName}!")

        if (currentName == "Ace" && yourTurn == true) {
            var input: Int = 0
            println("You drew an Ace! Do you want it to have the value of 1 or 10?")

            //input = readln().toInt()

            when(input) {
                1 -> currentValue -= 9
                10 -> currentValue -= 0
            }
        }

        if (yourTurn == true) {
            myScore += currentValue
        } else if (yourTurn == false) {
            enemyScore += currentValue
        }
        loopTimes++
    }
}