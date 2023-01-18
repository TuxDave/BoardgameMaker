package it.spaghetticode.bgm.core.game.logic.condition

import kotlinx.serialization.Serializable

@Serializable
sealed class Condition {
    abstract fun check(): Boolean
}