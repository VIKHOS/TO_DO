package com.mcb

import com.mcb.restmodel.CreateTodoListRequest
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import reactor.core.publisher.Mono
import java.net.URI

@Controller
@RequestMapping("/todos")
class TodoService @Autowired constructor(var repository: TodoListRepository)
{

    companion object {
        private val logger = LoggerFactory.getLogger(TodoService::class.java)
    }

    init {
        MockData(repository).initRepository()
    }

    @PostMapping
    fun createToDoList(@RequestBody request: Mono<CreateTodoListRequest>): Mono<ResponseEntity<*>>
    {

        return request.map {
            repository.save(TodoList(it.name))
        }.map {
            it.id
        }.map {
            //ResponseEntity.created(URI.create("/todo/"+it)).build()
            //ResponseEntity.ok("OK")
            ResponseEntity.created(URI.create("/todo/"+it)).body("Created")
        }

    }

    @GetMapping
    fun fetchTodoList(): ResponseEntity<*>
    {

        return ResponseEntity.ok(repository.findAll())
    }

}