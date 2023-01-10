import it.spaghetticode.bgm.core.game.logic.Edit
import it.spaghetticode.bgm.core.game.nodes.Folder
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.Json.Default.encodeToString

//import kotlinx.serialization.Serializable
//import kotlinx.serialization.decodeFromString
//import kotlinx.serialization.encodeToString
//import kotlinx.serialization.json.Json
//
//@Serializable
//sealed class A(
//    var first: String
//)
//
//@Serializable
//class B(
//    val second: String
//): A("f"){
//    override fun toString(): String {
//        return "$first $second"
//    }
//}
////    internal constructor(bs: B_Serializable): this(
////        bs.first,
////        bs.second
////    )
//
////
////@Serializable
////data class B_Serializable(val first: String, val second: String)
////class B_Serializer<T>: KSerializer<B<T>>{
////    override val descriptor: SerialDescriptor = B_Serializable.serializer().descriptor
////
////    override fun deserialize(decoder: Decoder): B<T> {
////        return B<T>(decoder.decodeSerializableValue(B_Serializable.serializer()))
////    }
////
////    override fun serialize(encoder: Encoder, value: B<T>) {
////        val bs = B_Serializable(value.first, value.second)
////        encoder.encodeSerializableValue(B_Serializable.serializer(), bs)
////    }
////}
//
//fun main() {
//    val b: A = B("s")
////    val a = A("we")
//    println(b is A)
//    println(b is B)
//
//    println(Json.encodeToString(b))
//    println(Json.decodeFromString<A>("{\"type\":\"B\",\"first\":\"s\",\"second\":\"s\"}"))
//}

open class A
class B: A()

//fun main() {
//    val b: A = B()
//    println(b::class)
//}

fun main() {
    val n = Folder()
    val n2 = Folder()
    val action = Edit(
        n.id,
        "name",
        17
    )
    val action2 = Edit(
        n2.id,
        "name",
        19
    )
    println(Json.encodeToString(action))
    println(Json.encodeToString(action2))
}