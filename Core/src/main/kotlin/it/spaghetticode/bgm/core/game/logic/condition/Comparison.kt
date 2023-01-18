package it.spaghetticode.bgm.core.game.logic.condition

import kotlinx.serialization.Serializable
import it.spaghetticode.bgm.core.game.logic.Reader

@Serializable
class Comparation(
    var type: ComparationType,
    var v1: Reader,
    var v2: Reader
): Condition(){
    override fun check(): Boolean {
        val first = v1.read()
        val second = v2.read()
        return when (type){
            ComparationType.EQUALS -> first?.equals(second) ?: false
            else -> {
                return if(first is Number && second is Number){
                    val first = first.toDouble()
                    val second = second.toDouble()
                    when(type){
                        ComparationType.LESS_THAN -> first < second
                        ComparationType.LESS_EQUALS_THAN -> first <= second
                        ComparationType.MORE_THAN -> first > second
                        ComparationType.MORE_EQUALS_THAN -> first >= second
                        else -> false //useless
                    }
                }else false
            }
        }
    }
}

enum class ComparationType(private val label: String) {
    EQUALS("== Equals"),
    LESS_THAN("< Strict Less Than"),
    LESS_EQUALS_THAN("<= Strait Less than"),
    MORE_THAN("> Strict More Than"),
    MORE_EQUALS_THAN(">= Strait More Than");

    override fun toString(): String {
        return this.label
    }}