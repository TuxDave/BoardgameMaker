package it.spaghetticode.bgm.core

import it.spaghetticode.bgm.core.game.logic.action.unit.Action
import it.spaghetticode.bgm.core.game.nodes.Folder
import it.spaghetticode.bgm.core.game.nodes.Node
import it.spaghetticode.bgm.core.game.player.Role
import it.spaghetticode.bgm.core.utils.IntRangeSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.lang.Integer.max

fun MutableList<Action>.execute(): Unit {
    this.forEach{
        it.execute()
    }
}

class Game(
    playerRange: IntRange = 1 .. 1,
    val roles: MutableList<Role> = mutableListOf(),
    val structure: Node = Folder("root"),
    /**
     * folder containing all the non-visible object (like data structures ecc)
     */
    val background: Folder = Folder("bg_root"),
    val setup: MutableList<Action> = mutableListOf()
){
    var playerRange: IntRange = 1 .. 1
        set(value) {
            field = if(value.first > 1 && value.last >= value.first){
                value
            }else{
                val start = max(value.first, 1)
//                val end = max(value.last, start)
                start .. max(value.last, start)
            }
        }

    init {
        this.playerRange = playerRange
    }

    internal constructor(gameSerializable: GameSerializable) : this(
        playerRange = gameSerializable.playerRange,
        structure = gameSerializable.structure,
        setup = gameSerializable.setup
    )
}

@Serializable
internal data class GameSerializable(
    @Serializable(with = IntRangeSerializer::class)
    val playerRange: IntRange,
    val structure: Node, //this must be Deserialized before other structures using the same nodes as refer
    val background: Folder, //this must be Deserialized before other structures using the same nodes as refer
    val setup: MutableList<Action>
)

class GameSerializer: KSerializer<Game>{
    override val descriptor: SerialDescriptor = GameSerializable.serializer().descriptor
    override fun serialize(encoder: Encoder, value: Game) {
        val gameSerializable = GameSerializable(
            value.playerRange,
            value.structure,
            value.background,
            value.setup
        )
        encoder.encodeSerializableValue(GameSerializable.serializer(), gameSerializable)
    }

    override fun deserialize(decoder: Decoder): Game {
        val gameSerializable = decoder.decodeSerializableValue(GameSerializable.serializer())
        return Game(gameSerializable)
    }

}