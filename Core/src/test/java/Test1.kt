import it.spaghetticode.bgm.core.Project
import java.io.File

fun main() {
    val p = Project.open(File("/home/tuxdave/Scaricati/Progetto1/Progetto1.bgm"))
    p.location = File("/home/tuxdave/Scaricati/Progetto1/Progetto1.bgm")
}