package com.mcb.inmemory

import com.mcb.common.*
import com.mcb.db.TodoListRepository
import com.mcb.db.TodoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.*
import java.net.URI




@RestController
@CrossOrigin(origins = ["*"])
@RequestMapping("/todos")
@Component
class TodoService @Autowired constructor(val todoRepository: TodoRepository){

    val todoManagement: IDataOperations = TodoManagement()


    @PostMapping("/{name}")
    fun createTodoList(@PathVariable name: String) : ResponseEntity<*>
    {
        return try {
            val todoListItem = todoRepository.createTodoList(name, mutableListOf())
            ResponseEntity.created(URI.create("/todos/" + todoListItem.id)).body(todoListItem)
        } catch (e: TodoException)
        {
            handleToDoException(e)
        }
    }

    @PostMapping("/item/{idList}")
    fun addItem(@PathVariable idList: Int, @RequestBody item: TodoItem): ResponseEntity<*>
    {
        return try {
            val todoItem = todoRepository.addItem(idList, item.description)
            ResponseEntity.created(URI.create("/todos/"+todoItem.id)).body(todoItem)
        } catch (e: TodoException)
        {
            handleToDoException(e)
        }

    }

    @DeleteMapping("item/{idList}/{idItem}")
    fun deleteItemFromList(@PathVariable idList: Int, @PathVariable idItem: Int): ResponseEntity<*>{

        return try {
            todoRepository.deleteItem(idList,idItem)
            ResponseEntity.ok(TodoResponse("Ok"))
        } catch (e: TodoException)
        {
            handleToDoException(e)
        }
    }

    @GetMapping
    fun listAllTodoList() : ResponseEntity<*>
    {
        val listTodo = todoRepository.fetchAll()
        return ResponseEntity.ok(listTodo)
    }
    @PutMapping("item/{idList}/{idItem}/{completeFlag}")
    fun updateItemFromList(@PathVariable idList: Int, @PathVariable idItem: Int, @PathVariable completeFlag:Boolean): ResponseEntity<*> {

        try {
            todoRepository.updateItem(idList, idItem, completeFlag)
            return ResponseEntity.ok(TodoResponse("Ok"))
        } catch (e: TodoException)
        {
           return handleToDoException(e)
        }
    }


    fun handleToDoException(ex: TodoException):ResponseEntity<*>
    {

        return when(ex.message)
        {
            ITEM_NOT_FOUND ->ResponseEntity.status(HttpStatus.NOT_FOUND).body(TodoResponse(ex.message))
            null -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex)
            else -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(TodoResponse(ex.message))
        }
    }




}