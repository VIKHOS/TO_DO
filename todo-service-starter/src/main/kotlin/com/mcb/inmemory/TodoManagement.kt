package com.mcb.inmemory

import com.mcb.common.*

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

        //checking if not empty
        if(todoList.items.size == 0)
        {
            throw TodoException(LIST_EMPTY)
        } else {
            val todoItem = todoList.items.find { todoItem: TodoItem -> todoItem.id == idItem }
            if (todoItem == null) {
                throw  TodoException(ITEM_NOT_FOUND)
            } else {
                todoList.items.remove(todoItem)
            }
        }

    }
    @Throws(Exception::class)
    override fun updateItem(idList: Int, idItem: Int, completeFlag: Boolean) {
        val todoList = fetchTodoListFromId(idList)
        val todoItem = todoList.items.find{ todoItem: TodoItem -> todoItem.id == idItem }
        if(todoItem==null){
            throw  TodoException(ITEM_NOT_FOUND)
        }
        else{
            if(todoItem.completed == completeFlag) {
                throw  TodoException(STATUS_ALREADY_SET)
            } else {
                todoItem.completed = completeFlag
            }
        }
    }

    @Throws(Exception::class)
    fun fetchTodoListFromId(idList: Int) : TodoList
    {
        val todoList = listTodoList.find{ todoList -> todoList.id == idList }
        if(todoList == null)
        {
            throw  TodoException(ITEM_NOT_FOUND)
        } else
        {
            return todoList
        }

    }

}