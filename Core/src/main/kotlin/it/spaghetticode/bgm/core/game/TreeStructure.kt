package it.spaghetticode.bgm.core.game

import it.spaghetticode.bgm.core.game.nodes.Folder
import it.spaghetticode.bgm.core.game.nodes.Node
import kotlinx.serialization.Serializable

@Serializable
class TreeStructure constructor (
    val root: Node = Folder()
)