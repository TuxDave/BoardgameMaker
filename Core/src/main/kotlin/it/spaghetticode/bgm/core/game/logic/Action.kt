package it.spaghetticode.bgm.core.game.logic

import it.spaghetticode.bgm.core.game.nodes.Node
import kotlinx.serialization.Serializable
import kotlin.reflect.KCallable
import kotlin.reflect.KProperty
import kotlin.reflect.KProperty1

/**
 * generic model for various operations like change a property value from a node or a selection ecc
 */
@Serializable
sealed class Action{
    abstract fun execute(): Unit
}