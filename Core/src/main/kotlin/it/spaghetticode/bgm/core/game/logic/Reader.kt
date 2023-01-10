package it.spaghetticode.bgm.core.game.logic

import kotlinx.serialization.Serializable

/**
 * generic reader able to perform a read from somewhere (node, file ecc)
 */
@Serializable
sealed class Reader<T>{
    abstract fun read(): T?
}