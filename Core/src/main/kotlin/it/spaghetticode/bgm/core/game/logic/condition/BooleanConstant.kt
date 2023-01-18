package it.spaghetticode.bgm.core.game.logic.condition

import kotlinx.serialization.Serializable

@Serializable
class BooleanConstant(var value: Boolean)
    : Condition(){
    override fun check(): Boolean {
        return value
    }
}