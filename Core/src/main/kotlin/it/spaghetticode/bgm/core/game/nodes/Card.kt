package it.spaghetticode.bgm.core.game.nodes

import it.spaghetticode.bgm.core.annotations.GameData
import it.spaghetticode.bgm.core.execute
import it.spaghetticode.bgm.core.game.logic.Action
import it.spaghetticode.bgm.core.utils.Size2D
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

/**
 * if this is in imagePath field will be searched in the jar, else in the assets folder in relative path mode
 */
const val DEFAULT_NOT_FOUND_CARD_IMAGE = "/assets/images/card_not_found.jpg"

@Serializable
class Card(
    @GameData val value: Int?,
    @GameData val seedName: String?,
    @GameData val description: String?,
    @GameData val imagePath: String = DEFAULT_NOT_FOUND_CARD_IMAGE, // TODO: the Editor should copy the media to the assets folder and link the "relative" path
    @GameData val effect: MutableList<Action> = mutableListOf(),
    @GameData val size: Size2D = Size2D(60, 90)
) : Node() {
    @Transient
    @GameData
    var deck: Deck? = null
        set(value) {
            field = value
            deck?.let {
                seed = it.seeds.find { it.name == seedName }
            }
        }

    @Transient
    var seed: Seed? = null
        set(value) {
            field = if (value?.name == seedName) value else null
        }

    fun executeEffect(): Unit {
        effect.execute()
    }
}

@Serializable
data class Seed(
    val name: String
)