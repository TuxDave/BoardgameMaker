package it.spaghetticode.bgm.core.game.logic.action.unit

import kotlinx.serialization.Serializable

@Serializable
sealed class NodeAction(
): Action(){
    abstract val nodeId: Int
}