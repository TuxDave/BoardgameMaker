package it.spaghetticode.bgm.core.game

import it.spaghetticode.bgm.core.game.nodes.Folder
import it.spaghetticode.bgm.core.game.nodes.Node
import kotlinx.serialization.Serializable

@Serializable
class TreeStructure<T> constructor (
    val root: TreeNode<T>
)

@Serializable
data class TreeNode<T> @JvmOverloads constructor(
    val siblings: MutableList<TreeNode<T>> = mutableListOf(),
    val parent: TreeNode<T>? = null,
    val content: T
)