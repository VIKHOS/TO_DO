package com.mcb

import com.mcb.restmodel.TodoItem
import com.mcb.restmodel.TodoList
import com.mcb.restmodel.TodoListRepository

class MockData constructor(var repository: TodoListRepository)
{

    fun initRepository()
    {
        var todoList= TodoList(
                "Work",
                items = listOf(
                    TodoItem("Code UI"), TodoItem("Code Backend")
                )
        )

        repository.save(todoList);

    }
}