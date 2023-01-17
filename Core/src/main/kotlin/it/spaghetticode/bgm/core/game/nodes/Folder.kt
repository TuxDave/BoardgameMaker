package it.spaghetticode.bgm.core.game.nodes

import kotlinx.serialization.Serializable

@Serializable
class Folder(): Node(){
    val isEmpty: Boolean
        get() {return siblings.isEmpty()}
}