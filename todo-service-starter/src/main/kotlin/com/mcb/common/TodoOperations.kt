package com.mcb.common

interface IDataOperations{

    fun createTodoList(name: String, items: List<TodoItem>)

    fun addItem(idList: Int, description: String)
}