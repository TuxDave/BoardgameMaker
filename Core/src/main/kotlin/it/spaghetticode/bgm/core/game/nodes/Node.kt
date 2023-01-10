package it.spaghetticode.bgm.core.game.nodes

import it.spaghetticode.bgm.core.annotations.GameData
import kotlinx.serialization.Serializable
import kotlin.reflect.KType
import kotlin.reflect.full.memberProperties

private val ids = mutableListOf<Int>()
private fun MutableList<Int>.generateNew(): Int{
    return if (ids.size > 0) ids.last() + 1 else 1
}

val registeredNodes: MutableList<Node> = mutableListOf<Node>()
fun MutableList<Node>.getById(id: Int): Node? {
    return registeredNodes.find { it.id == id }
}

/**
 * class representing the idea of Node
 * It is a "folder" that can contain others nodes and have a parent (null if was root of the tree)
 */
@Serializable
sealed class Node @JvmOverloads constructor(
    val siblings: MutableList<Node> = mutableListOf(),
    val parent: Node? = null,
    val id: Int = ids.generateNew(),
    @GameData
    var name: String = this::class.java.name.split(".").last().split("$").first(),
){
    init {
        register()
    }

    private fun register(): Unit{
        if(this !in registeredNodes) registeredNodes.add(this)
    }

    fun getAllGamePropertiesAndTypes(): Array<Pair<String, KType>> {
        val arr = mutableListOf<Pair<String, KType>>()
        val gd = GameData()
        for(mem in this.javaClass.kotlin.memberProperties.filter { gd in it.annotations }){
            arr.add(Pair(mem.name, mem.returnType))
        }
        return arr.toTypedArray()
    }

    override fun equals(other: Any?): Boolean {
        if(other !is Node) return false
        return id == (other as Node).id
    }
}