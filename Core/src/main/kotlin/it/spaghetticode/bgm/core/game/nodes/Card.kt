package it.spaghetticode.bgm.core.game.nodes

import it.spaghetticode.bgm.core.annotations.GameData
import it.spaghetticode.bgm.core.game.logic.Action
import it.spaghetticode.bgm.core.utils.Size2D
import kotlinx.serialization.Serializable
import javax.swing.ImageIcon

@Serializable
class Card(
    @GameData val value: Int?,
    @GameData val seed: Int?, //todo: refer to the Seed array in "background" Folder.kt in game
    @GameData val description: String?,
    @GameData val imagePath: String = "/assets/images/card_not_found.jpg", // TODO: make the system to save selected images to the "assets" folder of the project (don't know if from graphics or anywhere other)
    @GameData val effect: MutableList<Action> = mutableListOf(),
    @GameData val size: Size2D = Size2D(60,90)
) : Node()