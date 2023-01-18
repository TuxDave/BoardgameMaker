package it.spaghetticode.bgm.core.game.logic.action.reader

import kotlinx.serialization.Serializable
import kotlin.reflect.KType
import kotlin.reflect.full.createType
import kotlin.reflect.full.memberProperties
import kotlin.reflect.javaType

fun getKTypeFromValue(value: Any?): KType? {
    return value?.let {
        value::class.createType()
    }
}

@OptIn(ExperimentalStdlibApi::class)
fun getKTypeFromClassAttributeName(type: KType, attribute: String): KType?{
    return type.javaType.javaClass.kotlin.memberProperties.find {
        it.name == attribute
    }?.returnType
}

/**
 * generic reader able to perform a read from somewhere (node, file ecc)
 */
@Serializable
sealed class Reader{
    abstract fun read(): Any?
}