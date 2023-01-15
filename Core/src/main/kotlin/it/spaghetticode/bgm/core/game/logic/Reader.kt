package it.spaghetticode.bgm.core.game.logic

import kotlinx.serialization.Serializable
import kotlin.reflect.KType
import kotlin.reflect.full.createType

fun getKTypeFromValue(value: Any?): KType? {
    return value?.let {
        value::class.createType()
    }
}

/**
 * generic reader able to perform a read from somewhere (node, file ecc)
 */
@Serializable
sealed class Reader{
    abstract fun read(): Any?
}