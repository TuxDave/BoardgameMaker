package it.spaghetticode.bgm.core.game.logic

import it.spaghetticode.bgm.core.annotations.GameData
import it.spaghetticode.bgm.core.game.nodes.Node
import it.spaghetticode.bgm.core.game.nodes.registeredNodes
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import org.jetbrains.annotations.PropertyKey
import kotlin.reflect.*
import kotlin.reflect.full.memberProperties

/**
 * Action to edit a node member value
 */
@Serializable
class Edit<T>(
    override val nodeId: Int,
    var propertyToUpdate: String,
    var newValue: T,
): NodeAction() {

    @Transient
    val node: Node? = registeredNodes.find { it.id == nodeId }

    /**
     * the effect of this method is not garantited
     */
    override fun execute() {
        node?.let {
            val a = GameData()
            val v = node.javaClass.kotlin.memberProperties.filterIsInstance<KMutableProperty<*>>().find{
                it.name == propertyToUpdate && a in it.annotations
            }
            try{ v?.setter?.call(node, (newValue as T)) }
            catch (_: Exception){}
        }
    }
}