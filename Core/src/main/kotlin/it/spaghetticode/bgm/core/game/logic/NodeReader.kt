package it.spaghetticode.bgm.core.game.logic

import it.spaghetticode.bgm.core.game.nodes.Node
import it.spaghetticode.bgm.core.game.nodes.getById
import it.spaghetticode.bgm.core.game.nodes.registeredNodes
import kotlinx.serialization.Serializable
import kotlin.reflect.KType
import kotlin.reflect.full.memberProperties

@Serializable
class NodeReader (
    var nodeId: Int,
    var property: String
): Reader(){

    private val node: Node? = registeredNodes.getById(nodeId)

    override fun read(): Any? {
        return node?.let {
            try{
                node.javaClass.kotlin.memberProperties.find { it.name == property }?.getter?.call(node)
            }catch (e: ClassCastException) {null}
        }
    }
}