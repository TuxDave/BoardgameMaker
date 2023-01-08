package it.spaghetticode.bgm.core

import it.spaghetticode.bgm.core.game.nodes.Folder
import it.spaghetticode.bgm.core.game.nodes.Node
import it.spaghetticode.bgm.core.utils.IntRangeSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.lang.Integer.max

class Game(
    playerRange: IntRange = 1 .. 1,
    val structure: Node = Folder()
    // TODO: add the list of action (setup)
    //create an extension function for a list of Action to execute each in sequence
    //decide how to manage the setup (mutableList) because could create some problems in deserialization (different instance of the same object ecc)
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

    internal constructor(gameSerializable: GameSerializable) : this(
        playerRange = gameSerializable.playerRange,
        structure = gameSerializable.structure
    )
}

@Serializable
internal data class GameSerializable(
    @Serializable(with = IntRangeSerializer::class)
    val playerRange: IntRange,
    val structure: Node
)

class GameSerializer: KSerializer<Game>{
    override val descriptor: SerialDescriptor = GameSerializable.serializer().descriptor
    override fun serialize(encoder: Encoder, value: Game) {
        val gameSerializable = GameSerializable(
            value.playerRange,
            value.structure
        )
        encoder.encodeSerializableValue(GameSerializable.serializer(), gameSerializable)
    }

    override fun deserialize(decoder: Decoder): Game {
        val gameSerializable = decoder.decodeSerializableValue(GameSerializable.serializer())
        return Game(gameSerializable)
    }

}