package it.spaghetticode.bgm.core.game.player

import it.spaghetticode.bgm.core.game.logic.Action
import it.spaghetticode.bgm.core.game.logic.condition.Condition
import kotlinx.serialization.Serializable

@Serializable
data class Role (
    var name: String,
    var abilities: MutableList<MutableList<Action>> = mutableListOf(),
    var winCondition: Condition?
)