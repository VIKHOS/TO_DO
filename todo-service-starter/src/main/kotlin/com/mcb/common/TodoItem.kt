
package com.mcb.common

import java.util.*


data class TodoItem(val id: Int, val description: String, var completed: Boolean = false)

data class TodoList(val id:Int = 0, val name: String = "", val items : MutableList<TodoItem> = mutableListOf())


/*
 class TodoItem constructor()
{
    var id: Int = 0
    var description: String = ""
    var completed: Boolean = false

    constructor(desc: String): this()
    {
        description = desc
    }



}*/


