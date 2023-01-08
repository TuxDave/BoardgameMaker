package it.spaghetticode.bgm.core.game.logic

import it.spaghetticode.bgm.core.annotations.GameData
import it.spaghetticode.bgm.core.game.nodes.Node
import kotlinx.serialization.Serializable
import java.lang.IllegalArgumentException
import kotlin.reflect.*
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.declaredMembers
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible
import kotlin.reflect.jvm.javaGetter
import kotlin.reflect.jvm.reflect

/**
 * Action to edit a node member value
 */
@Serializable
class Edit<T>(
    var node: Node,
    var propertyToUpdate: String,
    var newValue: T
): Action() {

    /**
     * the effect of this method is not garantited
     */
    override fun execute() {
        val a = GameData()
        val v = node.javaClass.kotlin.memberProperties.filterIsInstance<KMutableProperty<*>>().find{
            it.name == propertyToUpdate && a in it.annotations
        }
        try{ v?.setter?.call(node, (newValue as T)) }
        catch (_: Exception){}
    }
}