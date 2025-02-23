package com.example.gamecarx

import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import org.junit.runners.Parameterized

class CardTest {
    private lateinit var cards_base: Cards
    private lateinit var card_base1: Card
    private lateinit var card_base2: Card
    @Before
    fun setUp() {
        //cards = Cards("C:\\Users\\johdu\\AndroidStudioProjects\\GameCarx\\app\\src\\main\\res\\dataset\\cards_base.csv")
        cards_base = Cards("src/main/assets/dataset/cards_base.csv")

        card_base1 = cards_base.getCards().get(1) // first card
        card_base2 = cards_base.getCards().get(2) // second card
    }

    @Test
    fun getKeywords() {
        var kwc1: List<String> = listOf("Poisonous")
        assertEquals(/* expected = */ kwc1, /* actual = */ card_base1.keywords)
        var kwc2: List<String> = "".split(",")
        assertEquals(/* expected = */ kwc2, /* actual = */ card_base2.keywords)
    }

    @Test
    fun getId_card() {
        var kwc1: String = "1"
        assertEquals(/* expected = */ kwc1, /* actual = */ card_base1.id_card)
        var kwc2: String = "2"
        assertEquals(/* expected = */ kwc2, /* actual = */ card_base2.id_card)
    }

    @Test
    fun getName() {
        var kwc1: String = "Axolotl Healer"
        assertEquals(/* expected = */ kwc1, /* actual = */ card_base1.name)
        var kwc2: String = "Bee Bear"
        assertEquals(/* expected = */ kwc2, /* actual = */ card_base2.name)
    }

    @Test
    fun getPower() {
        var kwc1: Int = 4
        assertEquals(/* expected = */ kwc1, /* actual = */ card_base1.power)
        var kwc2: Int = 8
        assertEquals(/* expected = */ kwc2, /* actual = */ card_base2.power)
    }

    @Test
    fun getDescription() {
        var kwc1: String = "Play: Gain 2 life points"
        assertEquals(/* expected = */ kwc1, /* actual = */ card_base1.description)
        var kwc2: String = "Cannot be blocked by creatures with 6 power or less"
        assertEquals(/* expected = */ kwc2, /* actual = */ card_base2.description)
    }
}