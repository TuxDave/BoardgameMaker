import it.spaghetticode.bgm.core.annotations.GameData
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.findAnnotation

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

class A(
    var first: Int,
    @GameData var second: Int
)

fun main() {
    val a = A(1,2)
    for(mem in A::class.declaredMemberProperties.filter { GameData() in it.annotations }){
        println(mem.get(a))
    }
}