package com.mcb.db

import com.mcb.common.IDataOperations
import com.mcb.common.TodoItem
import com.mcb.common.TodoList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@Component
class TodoRepository @Autowired constructor(private  val todoListRepository : TodoListRepository,
                                            private val todoItemRepository: TodoItemRepository) : IDataOperations {



    override fun createTodoList(name: String, items: MutableList<TodoItem>): TodoList
    {
        val id = todoListRepository.create(name)
        return TodoList(id, name, items)

    }




    override fun addItem(idList: Int, description: String): TodoItem {
        val id = todoItemRepository.create(description, idList)
        return TodoItem(id, description)
    }

    override fun fetchAll(): MutableList<TodoList> {
        var listTdList = todoListRepository.selectAll();

        return listTdList
    }


    override fun deleteItem(idList: Int, idItem: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateItem(idList: Int, idItem: Int, completeFlag: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}