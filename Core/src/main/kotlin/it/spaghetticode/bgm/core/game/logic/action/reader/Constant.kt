package it.spaghetticode.bgm.core.game.logic.action.reader

import kotlinx.serialization.Serializable
import kotlin.Double

/**
 * easily cast the read()'s return to Int
 */
@Serializable
class DoubleConstant(
    var value: Double
) : Reader(){
    override fun read(): Any {
        return value
    }
}

@Serializable
class StringConstant(
    var value: String
): Reader(){
    override fun read(): Any {
        return value
    }
}