package it.spaghetticode.bgm.editor

import it.spaghetticode.bgm.core.Project
import it.spaghetticode.bgm.editor.windows.Launcher
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.json.JSONArray
import java.io.*

val SETTINGS_PATH = System.getProperty("user.home") + "/.BoardgameMaker"
fun main() {
    val settings = File(SETTINGS_PATH)
    if(!settings.exists()){
        settings.mkdir()
    }else if(!settings.isDirectory){
        settings.delete()
        settings.mkdir()
    }
    loadSettings(settings)

    val l = Launcher()
    l.isVisible = true
    l.pack()
}

fun loadSettings(s: File): Unit {
    val settings = s.listFiles()

    //loading recent opened projects
    var temp = settings.filter { it.name == "recentProjects.json" }
    val recent: File = if (temp.isNotEmpty()) temp[0] else File(s.absolutePath + "/recentProjects.json")
    try{
        val temp = JSONArray(BufferedReader(FileReader(recent.absolutePath)).readText())
        for(i in 0 until temp.length()){
            val p = Json.decodeFromString<ProjectAndLocation>(temp.getJSONObject(i).toString())
            var exists = false
            for(pr in recentProjects){
                if(pr == p){
                    exists = true
                    break;
                }
            }
            if(!exists) {
                p.project.location = File(p.location)
                recentProjects.add(p)
            }
        }
    }catch (e: Exception){
        updateRecentFile(recent)
        recentProjects = Json.decodeFromString(BufferedReader(FileReader(recent.absolutePath)).readText())
    }

    // TODO: load settings
}

@JvmOverloads
fun updateRecentFile(recent: File = File("$SETTINGS_PATH/recentProjects.json")){
    recentProjects.removeDuplicates()
//    if(!recent.exists()) {
        recent.createNewFile()
        val writer = BufferedWriter(FileWriter(recent.absolutePath))
        writer.write(Json.encodeToString(recentProjects))
        writer.close()
//    }
}

object settings{

}

var recentProjects = mutableListOf<ProjectAndLocation>()
fun MutableList<ProjectAndLocation>.removeDuplicates(){
    val duplicatesIndexes = mutableListOf<Int>()
    for(i in 0 until this.size){
        if(i in duplicatesIndexes) continue
        for(j in i until this.size){
            if(i == j) continue
            if(this[i] == this[j]){
                duplicatesIndexes.add(i)
                break
            }
        }
    }
    for(i in duplicatesIndexes.size-1 downTo  0){
        this.removeAt(duplicatesIndexes[i])
    }
}

@Serializable
data class ProjectAndLocation(val project: Project, val location: String)