package it.spaghetticode.bgm.core.utils

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable
internal data class IntRangeSerializable(
    val start: Int,
    val endInclusive: Int
)

internal class IntRangeSerializer: KSerializer<IntRange>{
    override val descriptor: SerialDescriptor = IntRangeSerializable.serializer().descriptor

    override fun serialize(encoder: Encoder, value: IntRange) {
        val irs: IntRangeSerializable = IntRangeSerializable(value.first, value.last)
        encoder.encodeSerializableValue(IntRangeSerializable.serializer(), irs)
    }

    override fun deserialize(decoder: Decoder): IntRange {
        val irs: IntRangeSerializable = decoder.decodeSerializableValue(IntRangeSerializable.serializer())
        return irs.start .. irs.endInclusive
    }
}