package com.mcb.common

interface IDataOperations{

    fun createTodoList(name: String, items: MutableList<TodoItem>) : TodoList

    fun addItem(idList: Int, description: String): TodoItem

    fun fetchAll(): MutableList<TodoList>

    fun deleteItem(idList: Int, idItem: Int)

    fun updateItem(idList: Int, idItem: Int, completeFlag: Boolean)
}