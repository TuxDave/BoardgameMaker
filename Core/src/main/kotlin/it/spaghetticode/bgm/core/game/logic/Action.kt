package it.spaghetticode.bgm.core.game.logic

import kotlinx.serialization.Serializable

/**
 * generic model for various operations like change a property value from a node or a selection ecc
 */
@Serializable
sealed class Action{
    abstract fun execute(): Unit
}