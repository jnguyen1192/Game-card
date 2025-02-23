package com.example.gamecarx

import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import kotlin.math.log

class GameTest {
    private lateinit var game_: Game
    @Before
    fun setUp() {
        //cards = Cards("C:\\Users\\johdu\\AndroidStudioProjects\\GameCarx\\app\\src\\main\\res\\dataset\\cards_base.csv")
        game_ = Game()
    }
    @Test
    fun init_mode_0() {
        game_.init_mode_0()
        assertEquals(0,0)
    }
    @Test
    fun distribToPlayers_2_players() {
        game_.init_mode_0()
        // Player 1 has got 5 cards in draw
        assertEquals(5,game_.players[0].drawing_cards.size)
        // Player 1 has got 5 cards in hand
        assertEquals(5,game_.players[0].playing_cards.size)
        // Player 1 has got 2 mindbugs
        assertEquals(2,game_.players[0].mindbug)
        // Player 1 has got 0 mindfrog
        assertEquals(0,game_.players[0].mindfrog)

        // Player 2 has got 5 in draw
        assertEquals(5,game_.players[1].drawing_cards.size)
        // Player 2 has got 5 cards in hand
        assertEquals(5,game_.players[1].playing_cards.size)
        // Player 2 has got 2 mindbugs
        assertEquals(2,game_.players[1].mindbug)
        // Player 2 has got 0 mindfrog
        assertEquals(0,game_.players[1].mindfrog)
        // OPTION check if there was no mistake and all cards are here
        // OPTION do the same test with 4 players
    }

    @Test
    fun setPlayerStart_2_players_0() {
        // Test if the cards to know the turn are OK
        game_.init_mode_0()
        // compare total cards to basic cards number
        var cardsTurnSize = game_.cardsTurn.size
        assertEquals(
            /* expected = */ 48,
            /* actual = */ game_.cardsTurn.size + game_.players[0].playing_cards.size +
                    game_.players[0].drawing_cards.size + game_.players[1].playing_cards.size +
                    game_.players[1].drawing_cards.size + game_.cards_base.getCards().size
        )
        // check if the player turn is good with the two last cards
        if(game_.cardsTurn[cardsTurnSize - 2].power > game_.cardsTurn[cardsTurnSize - 2].power) {
            assertEquals(0, game_.playerTurn)
        }
        if(game_.cardsTurn[cardsTurnSize - 2].power < game_.cardsTurn[cardsTurnSize - 2].power) {
            assertEquals(1, game_.playerTurn)
        }
    }

    @Test
    fun turn_2_players_0() {
        // check if the first turn work
        game_.init_mode_0()
        if(game_.playerTurn == 0) {
            // case p1 begin
            game_.turn()
            if(game_.players[1].mindbug == 1) {// p2 mindbugs
                // check card go to p2 board
                assertEquals(1, game_.players[1].board_cards.size)
                assertEquals(0, game_.players[0].board_cards.size)
                // check if it is p1 turn again
                assertEquals(0, game_.playerTurn)
            }
            else {// p2 don't mindbug
                // check card go to p1 board
                assertEquals(1, game_.players[0].board_cards.size)
                assertEquals(0, game_.players[1].board_cards.size)
                // check if it is p1 turn again
                assertEquals(1, game_.playerTurn)
            }
        }
    }

    @Test
    fun fight_only_power_2_players_0() {
        for (i in 1..100) {
            // test with 100 first turn

            // create a game with two player playing the first card each hand
            game_.init_mode_0()
            game_.players[0].playCard(0)
            game_.players[1].playCard(0)
            var p1CardPower = game_.players[0].board_cards[0].power
            var p2CardPower = game_.players[1].board_cards[0].power
            // first player attack second player which defend with its only card
            game_.fight(0, 0, game_.players[0], game_.players[1])

            if (p1CardPower == p2CardPower) {
                // no cards on boards
                assertEquals(
                    0,
                    game_.players[0].board_cards.size + game_.players[1].board_cards.size
                )
            } else if (p1CardPower > p2CardPower) {
                // only card on p1 board
                assertEquals(1, game_.players[0].board_cards.size)
                assertEquals(0, game_.players[1].board_cards.size)
            } else {
                // only card on p2 board
                assertEquals(0, game_.players[0].board_cards.size)
                assertEquals(1, game_.players[1].board_cards.size)
            }
        }
    }

    @Test
    fun useMindbug_without_effect_2_players_0() {
        // first player play a card on the board
        game_.init_mode_0()
        game_.players[0].playCard(0)
        var mindbuged_card = game_.players[0].board_cards[0]

        // check that first player has one card on board
        assertEquals(1, game_.players[0].board_cards.size)
        // check that second player has zero card on board
        assertEquals(0, game_.players[1].board_cards.size)
        // check second player mindbugs
        assertEquals(2, game_.players[1].mindbug)
        // second player use mindbug
        game_.useMindbug(game_.players[1], game_.players[0])
        // check that first player has zero card on board
        assertEquals(0, game_.players[0].board_cards.size)
        // check that second player has one card on board
        assertEquals(1, game_.players[1].board_cards.size)
        // check second player mindbugs
        assertEquals(1, game_.players[1].mindbug)
        // check right card is give to player 2
        assertEquals(mindbuged_card, game_.players[1].board_cards[0])
    }
    // TODO jeu en entier avec tous les états intermédiaires
    // TODO jeu en entier avec tous les états initiaux et finales
}