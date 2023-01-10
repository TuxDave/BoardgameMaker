package it.spaghetticode.bgm.core.game.logic

import it.spaghetticode.bgm.core.game.nodes.Node
import it.spaghetticode.bgm.core.game.nodes.getById
import it.spaghetticode.bgm.core.game.nodes.registeredNodes
import kotlinx.serialization.Serializable

@Serializable
sealed class NodeAction(
): Action(){
    abstract val nodeId: Int
}