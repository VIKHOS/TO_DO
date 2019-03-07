package com.mcb.inmemory

import com.mcb.common.IDataOperations
import com.mcb.common.TodoItem
import com.mcb.common.TodoList
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/todos")
class TodoService{

    val ITEM_NOT_FOUND = -10
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
        } catch (e: ClassNotFoundException)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ITEM_NOT_FOUND)
        }

    }

    @DeleteMapping("item/{idList}/{idItem}")
    fun deleteItemFromList(@PathVariable idList: Int, @PathVariable idItem: Int): ResponseEntity<*>{

        try {
            todoManagement.deleteItem(idList,idItem)
            return ResponseEntity.ok("OK")
        } catch (e: ClassNotFoundException)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ITEM_NOT_FOUND)
        }
    }

    @GetMapping
    fun listAllTodoList() : ResponseEntity<*>
    {
        val listTodo = todoManagement.fetchAll()
        return ResponseEntity.ok(listTodo)
    }
    @PutMapping("item/{idList}/{idItem}/{completeFlag" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "























}")
    fun updateItemFromList(@PathVariable idList: Int, @PathVariable idItem: Int, @PathVariable completeFlag:Boolean): ResponseEntity<*>{

        try {
            todoManagement.updateItem(idList,idItem,completeFlag)
            return ResponseEntity.ok("OK")
        } catch (e: ClassNotFoundException)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ITEM_NOT_FOUND)
        }
    }




}