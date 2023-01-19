package it.spaghetticode.bgm.core.game.logic.action.reader

import kotlinx.serialization.Serializable

/**
 * easily cast the read() return to Int
 */
@Serializable
class IntConstant(
    var value: Int
) : Reader(){
    override fun read(): Any {
        return value
    }
}

// TODO: create for strings and double 