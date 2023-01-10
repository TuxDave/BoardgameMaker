package it.spaghetticode.bgm.core.game.logic

import kotlinx.serialization.Serializable

@Serializable
sealed class NodeAction(
): Action(){
    abstract val nodeId: Int
}