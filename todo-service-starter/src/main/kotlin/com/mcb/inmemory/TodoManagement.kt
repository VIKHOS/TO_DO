package com.mcb.inmemory

import com.mcb.common.IDataOperations
import com.mcb.common.TodoItem
import com.mcb.common.TodoList

class TodoManagement : IDataOperations {

    val listTodoList: MutableList<TodoList> =  mutableListOf()
    var idList : Int = 1
    var idItem: Int = 1

    override fun createTodoList(name: String, items: MutableList<TodoItem>) {
        val todoList = TodoList(idList, name, items)
        idList++
        listTodoList.add(todoList)
    }

    @Throws(Exception::class)
    override fun addItem(idList: Int, description: String) {
        val todoList = listTodoList.find{ todoList -> todoList.id == idList }
        if(todoList == null)
        {
            throw ClassNotFoundException()
        } else
        {
            val item = TodoItem(idItem, description)
            todoList.items.add(item)
        }

    }

}