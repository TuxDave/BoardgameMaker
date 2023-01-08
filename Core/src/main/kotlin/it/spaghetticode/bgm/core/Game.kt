package it.spaghetticode.bgm.core

import com.sun.source.tree.BinaryTree
import it.spaghetticode.bgm.core.game.TreeStructure
import it.spaghetticode.bgm.core.utils.IntRangeSerializer
import kotlinx.serialization.Contextual
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.lang.Integer.max
import java.util.TreeSet

class Game(
    playerRange: IntRange
){
    var playerRange: IntRange = 1 .. 1
        set(value) {
            field = if(value.first > 1 && value.last >= value.first){
                value
            }else{
                var start = max(value.first, 1)
                var end = max(value.last, start)
                start .. end
            }
        }

    init {
        this.playerRange = playerRange
    }
    constructor() : this(1..1)

    internal constructor(gameSerializable: GameSerializable) : this(
        playerRange = gameSerializable.playerRange,
    )


}

@Serializable
internal data class GameSerializable(
    @Serializable(with = IntRangeSerializer::class)
    val playerRange: IntRange, // TODO: learn why doesn't deserialize right
)

class GameSerializer: KSerializer<Game>{
    override val descriptor: SerialDescriptor = GameSerializable.serializer().descriptor
    override fun serialize(encoder: Encoder, value: Game) {
        val gameSerializable = GameSerializable(
            value.playerRange
        )
        encoder.encodeSerializableValue(GameSerializable.serializer(), gameSerializable)
    }

    override fun deserialize(decoder: Decoder): Game {
        val gameSerializable = decoder.decodeSerializableValue(GameSerializable.serializer())
        return Game(gameSerializable)
    }

}