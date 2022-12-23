package it.spaghetticode.bgm.editor

import java.io.File
import javax.swing.JFrame
import javax.swing.filechooser.FileFilter

fun switchView(from: JFrame, to: JFrame): Unit {
    Thread(){
        to.isVisible = true
    }.start()
    from.dispose()
    println(Thread.getAllStackTraces().size)
}

object bgmFileFilter : FileFilter(){
    override fun accept(file: File): Boolean {
        return file.name.endsWith(".bgm") || file.isDirectory
    }

    override fun getDescription(): String {
        return "BoardGame Maker Project (.gbm)"
    }

    fun toJavaFileFilter(): java.io.FileFilter {
        return java.io.FileFilter(){
            file -> accept(file)
        }
    }
}