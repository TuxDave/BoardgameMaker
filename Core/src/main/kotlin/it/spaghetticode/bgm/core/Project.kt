package it.spaghetticode.bgm.core

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.*

@Serializable
class Project(
    private var name: String,
    val description: String
) {
    init {
        name = if (name != "") {
            name
        } else {
            "Project"
        }
    }

    fun save(path: String): Boolean {
        var path = path
        if(!path.endsWith("/")) path += "/"
        try {
            if(!File("$path$name/").mkdir()) throw IOException()
            val writer = BufferedWriter(FileWriter("$path$name/$name.bgm"))
            writer.write(Json.encodeToString(this))
            writer.close()

            // TODO: creare cartelle ecc 
        } catch (e: IOException) {
            e.printStackTrace()
            return false;
        }
        return true;
    }

    companion object {
        @JvmStatic
        @Throws(FileNotFoundException::class)
        fun open(file: File): Project {
            if (file.exists()) {
                val reader = BufferedReader(FileReader(file))
                val p = Json.decodeFromString<Project>(reader.readText())
                reader.close()
                return p
                // TODO: test this 
            } else {
                throw FileNotFoundException("File '${file.absoluteFile}': not found")
            }
        }
    }
}