package it.spaghetticode.bgm.core.game.nodes

import it.spaghetticode.bgm.core.annotations.GameData
import kotlinx.serialization.Serializable

@Serializable
class Deck(
    @GameData val deckName: String,
    /**
     * recommended to not use this field to add cards, use the aposit method on Deck::class
     */
    @GameData val cards: MutableList<Card> = mutableListOf(),
    @GameData val seeds: MutableList<Seed> = mutableListOf()
) : Node(){
    init {
        for(card in cards){
            card.deck = this
        }
    }

    fun addCard(card: Card): Unit {
        cards.add(card)
        card.seed = seeds.find { it.name == card.seedName }
    }
}