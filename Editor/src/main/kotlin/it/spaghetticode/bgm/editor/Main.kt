package it.spaghetticode.bgm.editor

import it.spaghetticode.bgm.core.Project
import it.spaghetticode.bgm.editor.windows.Launcher
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.lang.Exception

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
        recentProjects = Json.decodeFromString(BufferedReader(FileReader(recent.absolutePath)).readText())
    }catch (e: Exception){
        updateRecentFile(recent)
        recentProjects = Json.decodeFromString(BufferedReader(FileReader(recent.absolutePath)).readText())
    }

    // TODO: load settings

    println(recentProjects)
}

@JvmOverloads
fun updateRecentFile(recent: File = File("$SETTINGS_PATH/recentProjects.json")){
//    if(!recent.exists()) {
        recent.createNewFile()
        val writer = BufferedWriter(FileWriter(recent.absolutePath))
        writer.write(Json.encodeToString(recentProjects))
        writer.close()
//    }
}

object settings{

}

var recentProjects = mutableListOf<String>()