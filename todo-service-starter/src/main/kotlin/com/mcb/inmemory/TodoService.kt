package com.mcb.inmemory

import com.mcb.common.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI




@RestController
@RequestMapping("/todos")
class TodoService{

    val todoManagement: IDataOperations = TodoManagement()

    @PostMapping("/{name}")
    fun createTodoList(@PathVariable name: String) : ResponseEntity<*>
    {
        val todoListItem = todoManagement.createTodoList(name, mutableListOf())
        return ResponseEntity.created(URI.create("/todos/"+todoListItem.id)).body(todoListItem)
    }

    @PostMapping("/item/{idList}")
    fun addItem(@PathVariable idList: Int, @RequestBody item: TodoItem): ResponseEntity<*>
    {
        try {
            val todoItem = todoManagement.addItem(idList, item.description)
            return ResponseEntity.created(URI.create("/todos/"+todoItem.id)).body(todoItem)
        } catch (e: TodoException)
        {
            return handleToDoException(e)
        }

    }

    @DeleteMapping("item/{idList}/{idItem}")
    fun deleteItemFromList(@PathVariable idList: Int, @PathVariable idItem: Int): ResponseEntity<*>{

        try {
            todoManagement.deleteItem(idList,idItem)
            return ResponseEntity.ok("OK")
        } catch (e: TodoException)
        {
            return handleToDoException(e)
        }
    }

    @GetMapping
    fun listAllTodoList() : ResponseEntity<*>
    {
        val listTodo = todoManagement.fetchAll()
        return ResponseEntity.ok(listTodo)
    }
    @PutMapping("item/{idList}/{idItem}/{completeFlag}")
    fun updateItemFromList(@PathVariable idList: Int, @PathVariable idItem: Int, @PathVariable completeFlag:Boolean): ResponseEntity<*> {

        try {
            todoManagement.updateItem(idList, idItem, completeFlag)
            return ResponseEntity.ok("OK")
        } catch (e: TodoException)
        {
           return handleToDoException(e)
        }
    }


    fun handleToDoException(ex: TodoException):ResponseEntity<*>
    {
        return when(ex.message)
        {
            ITEM_NOT_FOUND ->ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
            else -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.message)
        }
    }




}