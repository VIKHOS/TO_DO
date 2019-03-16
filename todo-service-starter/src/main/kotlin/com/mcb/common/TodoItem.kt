
package com.mcb.common


data class TodoItem(val id: Int, val description: String, var completed: Boolean = false)

data class TodoList(val id:Int = 0, val name: String = "", val items : MutableList<TodoItem> = mutableListOf())

