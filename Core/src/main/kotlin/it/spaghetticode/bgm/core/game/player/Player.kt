package it.spaghetticode.bgm.core.game.player

import it.spaghetticode.bgm.core.game.logic.condition.Condition

//NOT FUCKING SERIALIZABLE
data class Player(
    var name: String,
    var role: Role,
    var winCondition: Condition?
){
    /**
     * fun that checks if winCondition was satisfied
     */
    fun won(): Pair<Boolean, WinType> {
        return winCondition?.let {
            Pair(it.check(), WinType.SINGLE)
        } ?: Pair(false, WinType.ROLE)
    }
}

enum class WinType{
    SINGLE,
    ROLE
}