package com.mcb

import com.mcb.restmodel.TodoItem
import com.mcb.restmodel.TodoList
import com.mcb.restmodel.TodoListRepository
import com.mcb.restmodel.TodoService
import org.slf4j.LoggerFactory

class MockData constructor(var repository: TodoListRepository)
{

    companion object {
        private val logger = LoggerFactory.getLogger(MockData::class.java)
    }

    fun initRepository()
    {
        logger.debug("init Repository called")
        var todoList= TodoList(
                "Work",
                items = listOf(
                    TodoItem("Code UI"), TodoItem("Code Backend")
                )
        )

        repository.save(todoList);

    }
}