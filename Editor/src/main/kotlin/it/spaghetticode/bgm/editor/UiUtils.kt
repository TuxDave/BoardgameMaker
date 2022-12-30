package it.spaghetticode.bgm.editor

import it.spaghetticode.bgm.core.Project
import java.awt.Color
import java.awt.Component
import java.io.File
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JList
import javax.swing.ListCellRenderer
import javax.swing.filechooser.FileFilter

fun switchView(from: JFrame, to: JFrame): Unit {
    Thread(){
        to.isVisible = true
    }.start()
    from.dispose()
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

class ProjectListRenderer : JLabel(), ListCellRenderer<Project>{
    override fun getListCellRendererComponent(
        p0: JList<out Project>?,
        p1: Project?,
        p2: Int,
        selected: Boolean,
        p4: Boolean
    ): Component {
        text = p1?.name ?: "NOT FOUND"
        background = if(selected) Color.lightGray else Color.white
        isOpaque = true
        return this
    }
}