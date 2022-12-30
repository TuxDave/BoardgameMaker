package it.spaghetticode.bgm.core

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.*
import kotlin.RuntimeException

@Serializable
class Project(
    var name: String,
    val description: String,
) {
    @Transient //non serializza questo
    var location: File? = null
        @Throws(ProjectException::class)
        set(value) {
            if(value == null) {
                field = null
                return
            }
            val e = ProjectException("location attribute must be a directory (not null) containing a bgm project")
            if (!value.isDirectory ){
                throw e
            } else {
                var ok = false
                for(file in value.listFiles()){
                    if(file.name.endsWith(".bgm")){
                        ok = true
                        break
                    }
                }
                if(ok) field = value
                else throw e
            }
        }
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
            if(!File("$path$name/").mkdir()) throw ProjectException("Unable to create project in specified" +
                    " location: project with same name already exists!")
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
            } else {
                throw FileNotFoundException("File '${file.absoluteFile}': not found")
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if(other !is Project) return false
        return name == other.name && description == other.description
    }
}

class ProjectException(msg: String = "") : RuntimeException(msg)