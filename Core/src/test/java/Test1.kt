import it.spaghetticode.bgm.core.game.logic.NodeReader
import it.spaghetticode.bgm.core.game.logic.Reader
import it.spaghetticode.bgm.core.game.logic.getKTypeFromClassAttributeName
import it.spaghetticode.bgm.core.game.logic.getKTypeFromValue
import it.spaghetticode.bgm.core.game.nodes.Folder
import it.spaghetticode.bgm.core.utils.Size2D
import java.lang.reflect.Type
import kotlin.reflect.cast
import kotlin.reflect.full.createType
import kotlin.reflect.javaType
import kotlin.reflect.jvm.javaType
import kotlin.reflect.typeOf

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
//    val n = Folder()
//    n.getAllGamePropertiesAndTypes().forEach {
////        println("$it")
//    }
//    val reader = NodeReader(n.id, "nname")
//    val intero: Any = 5
////    println(intero::class.createType() == typeOf<Int>())
////    println(reader.read()!!::class.cast(reader.read())::class)
//    println(getKTypeFromValue(reader.read()))
//    println(getKTypeFromClassAttributeName(n::class.createType(), "name"))
//    println(n.isEmpty)
//    n.siblings.add(n)
//    println(n.isEmpty)7
    val s = Size2D(10,-32)
    s.setHeight(-23)
    println(s.getHeight())
}