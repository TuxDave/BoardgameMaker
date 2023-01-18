import it.spaghetticode.bgm.core.game.logic.condition.ComparationType
import it.spaghetticode.bgm.core.game.nodes.Card
import it.spaghetticode.bgm.core.game.nodes.Deck
import it.spaghetticode.bgm.core.game.nodes.Seed

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

//fun main() {
////    val n = Folder()
////    n.getAllGamePropertiesAndTypes().forEach {
//////        println("$it")
////    }
////    val reader = NodeReader(n.id, "nname")
////    val intero: Any = 5
//////    println(intero::class.createType() == typeOf<Int>())
//////    println(reader.read()!!::class.cast(reader.read())::class)
////    println(getKTypeFromValue(reader.read()))
////    println(getKTypeFromClassAttributeName(n::class.createType(), "name"))
////    println(n.isEmpty)
////    n.siblings.add(n)
////    println(n.isEmpty)7
//    val s = Size2D(10,-32)
//    s.setHeight(-23)
//    println(s.getHeight())
//}

fun main() {
    val deck = Deck(
        "prova",
        cards = mutableListOf(
            Card(
                1,
                "picche",
                "descrizione",
            )
        ),
        seeds = mutableListOf(
            Seed("picche")
        )
    )
    println(deck.cards[0].seed)
    val t = ComparationType.LESS_EQUALS_THAN
    println(t)
}