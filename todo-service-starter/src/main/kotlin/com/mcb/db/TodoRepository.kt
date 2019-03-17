package com.mcb.db

import com.mcb.common.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@Component
class TodoRepository @Autowired constructor(private  val todoListRepository : TodoListRepository,
                                            private val todoItemRepository: TodoItemRepository) : IDataOperations {


    @Throws(Exception::class)
    override fun createTodoList(name: String, items: MutableList<TodoItem>): TodoList
    {
        if(name.trim() == "") {
            throw TodoException(BLANK_INPUT)
        } else {
            //check for duplicates
            val existingItem = fetchAll().find { todoList: TodoList -> todoList.name == name }
            if (existingItem != null) {
                throw TodoException(ALREADY_EXISTS)
            } else {
                val id = todoListRepository.create(name)
                return TodoList(id, name, items)
            }
        }

    }



    @Throws(Exception::class)
    override fun addItem(idList: Int, description: String): TodoItem {
        if(description.trim() == "") {
            throw TodoException(BLANK_INPUT)
        } else {
            //check for duplicates
            val todoItems = todoItemRepository.selectListItems(idList)
            val existingItem = todoItems.find { todoItem: TodoItem -> todoItem.description == description }
            if (existingItem != null) {
                throw TodoException(ALREADY_EXISTS)
            } else {
                val id = todoItemRepository.create(description, idList)
                return TodoItem(id, description)
            }
        }
    }

    override fun fetchAll(): MutableList<TodoList> {

        var listTdList = todoListRepository.selectAll()
        return listTdList
    }


    @Throws(Exception::class)
    override fun deleteItem(idList: Int, idItem: Int) {
        //checking if not empty
        if(todoItemRepository.selectListItems(idList).size == 0)
        {
            throw TodoException(LIST_EMPTY)
        } else {
            val rowCount = todoItemRepository.deleteItem(idItem, idList)
            if (rowCount == 0) {
                throw  TodoException(ITEM_NOT_FOUND)
            }
        }
    }

    @Throws(Exception::class)
    override fun updateItem(idList: Int, idItem: Int, completeFlag: Boolean) {
        val rowCount = todoItemRepository.updateItem(idItem, idList, completeFlag)
        if (rowCount == 0) {
            throw  TodoException(ITEM_NOT_FOUND)
        }
    }
}