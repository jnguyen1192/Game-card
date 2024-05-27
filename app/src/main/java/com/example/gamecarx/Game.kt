package com.example.gamecarx

class Game {
    var nb_player: Int
    var mode: Int
    var playerTurn: Int
    lateinit var players: MutableList<Player>

    constructor(nb_player: Int=2, mode: Int=0) {
        // nb_player can be 2 or 4
        // mode can be 0 as normal, 1 first extension, ...
        this.nb_player = nb_player
        this.mode = mode
        this.playerTurn = 0
        this.players = mutableListOf()
    }

    fun distribToPlayers(cards_base:Cards) {
        for (i in 1..nb_player) {
            players.add(Player(cards_base.giveCards()))
        }
    }

    // TODO 1) mode_0
    fun init_mode_0() {
        // Get cards with correct path
        var cards_base: Cards = Cards("src/main/assets/dataset/cards_base.csv")
        cards_base.shuffleCards()
        // 48 cards
        // distrib cards to players
        distribToPlayers(cards_base)
        // TODO first card to p1
        // second card to p2
        // if p1 > p2
        // p1 play first else p2 play first


        // TODO display  10 drawing cards with number on it for each player
        // TODO display life points
        // TODO draw the cards 5 for players and hide enemy cards
        // TODO turn
        // TODO rule: put a card or attack if you can't you loose
        // TODO rule: at the beginning of a turn if you have less than 1 life point you loose
        // TODO rule: at the beginning of a turn you have to defend or you loose 1 or more life point

        // Case two players
        // Case four players
    }
        // TODO 2) tests
}