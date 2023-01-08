package it.spaghetticode.bgm.core.game.nodes

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

private var IDS = 1

@Serializable
sealed class Node @JvmOverloads constructor(
    var name: String = this::class.simpleName!!,
){
    @Transient
    val id: Int = IDS++

    override fun equals(other: Any?): Boolean {
        if(other !is Node) return false
        return id == (other as Node).id
    }
}