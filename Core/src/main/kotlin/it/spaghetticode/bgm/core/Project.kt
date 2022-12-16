package it.spaghetticode.bgm.core

import kotlinx.serialization.Serializable

@Serializable
class Project (
    _name: String,
    val description: String
){
    var name: String = ""
        set(value) {
            field = if(value != ""){
                value
            }else{
                "Project"
            }
        }

    init {
        name = _name
    }
}