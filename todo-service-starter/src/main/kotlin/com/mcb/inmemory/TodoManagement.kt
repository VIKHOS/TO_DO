package com.mcb.inmemory

import com.mcb.common.IDataOperations
import com.mcb.common.TodoItem
import com.mcb.common.TodoList

class TodoManagement : IDataOperations {



    private val listTodoList: MutableList<TodoList> =  mutableListOf()
    private var idList : Int = 1
    private var idItem: Int = 1

    override fun createTodoList(name: String, items: MutableList<TodoItem>) : TodoList {
        val todoList = TodoList(idList, name, items)
        idList++
        listTodoList.add(todoList)
        return todoList
    }

    @Throws(Exception::class)
    override fun addItem(idList: Int, description: String) : TodoItem {
        val todoList = fetchTodoListFromId(idList)
        val item = TodoItem(idItem, description)
        todoList.items.add(item)
        idItem++
        return item


    }

    override fun fetchAll(): MutableList<TodoList> {
        return listTodoList
    }

    @Throws(Exception::class)
    override fun deleteItem(idList: Int, idItem: Int) {
        val todoList = fetchTodoListFromId(idList)
        val todoItem = todoList.items.find{ todoItem: TodoItem -> todoItem.id == idItem }
        if(todoItem==null){
            throw ClassNotFoundException()
        }
        else{
            todoList.items.remove(todoItem)
        }

    }
    @Throws(Exception::class)
    override fun updateItem(idList: Int, idItem: Int, completeFlag: Boolean) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        val todoList = fetchTodoListFromId(idList)
        val todoItem = todoList.items.find{ todoItem: TodoItem -> todoItem.id == idItem }
        if(todoItem==null){
            throw ClassNotFoundException()
        }
        else{
            todoItem.completed=completeFlag
        }
    }

    @Throws(Exception::class)
    fun fetchTodoListFromId(idList: Int) : TodoList
    {
        val todoList = listTodoList.find{ todoList -> todoList.id == idList }
        if(todoList == null)
        {
            throw ClassNotFoundException()
        } else
        {
            return todoList
        }

    }

}