package it.spaghetticode.bgm.core.game.logic

import it.spaghetticode.bgm.core.game.nodes.Node
import it.spaghetticode.bgm.core.game.nodes.getById
import it.spaghetticode.bgm.core.game.nodes.registeredNodes
import kotlinx.serialization.Serializable
import kotlin.reflect.KType
import kotlin.reflect.full.memberProperties

@Serializable
class NodeReader <T> (
    var nodeId: Int,
    var property: String
): Reader<T>(){

    private val node: Node? = registeredNodes.getById(nodeId)

    override fun read(): T? {
        return node?.let {
            try{
                node.javaClass.kotlin.memberProperties.find { it.name == property }?.getter?.call(node) as T?

            }catch (e: ClassCastException) {null}
        }
    }
}