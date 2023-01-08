package it.spaghetticode.bgm.core.game.nodes

import it.spaghetticode.bgm.core.annotations.GameData
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

private var IDS = 1

/**
 * class representing the idea of Node
 * It is a "folder" that can contain others nodes and have a parent (null if was root of the tree)
 */
@Serializable
sealed class Node @JvmOverloads constructor(
    val siblings: MutableList<Node> = mutableListOf(),
    val parent: Node? = null,
    var name: String = this::class.java.name.split(".").last().split("$").first(),
){
    @Transient
    val id: Int = IDS++

    override fun equals(other: Any?): Boolean {
        if(other !is Node) return false
        return id == (other as Node).id
    }
}