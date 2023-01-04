package it.spaghetticode.bgm.core

import it.spaghetticode.bgm.core.utils.IntRangeSerializer
import kotlinx.serialization.Contextual
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.lang.Integer.max

class Game(
    playerRange: IntRange
){
    var playerRange: IntRange = 1 .. 1
        set(value) {
            if(value.start > 1 && value.endInclusive >= value.start){
                field = value
            }else{
                var start = max(value.start, 1)
                var end = max(value.endInclusive, start)
                field = start .. end
            }
        }

    constructor() : this(1..1)

    internal constructor(gameSerializable: GameSerializable) : this(
        gameSerializable.playerRange
    )
}

@Serializable
internal data class GameSerializable(
    @Serializable(with = IntRangeSerializer::class)
    val playerRange: IntRange,
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