package it.spaghetticode.bgm.core.utils

import kotlinx.serialization.Serializable
import kotlin.math.max

@Serializable
data class Size2D(
    private var width: Int,
    private var height: Int
) {

    fun setWidth(value: Int): Unit {
        width = max(value, 0)
    }
    fun getWidth(): Int = width
    fun setHeight(value: Int): Unit{
        height = max(value, 0)
    }
    fun getHeight(): Int = height

    init {
       setWidth(max(width, 0))
       setHeight(max(height, 0))
    }
}