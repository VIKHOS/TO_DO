package com.mcb

import org.springframework.data.repository.CrudRepository
import java.util.*


interface TodoListRepository:CrudRepository<TodoList,Long>{

    override fun <S: TodoList>save(todoList: S): S

    override fun <S: TodoList>saveAll(entities: Iterable<S>) : Iterable<S>

    override fun findById(id: Long): Optional<TodoList>

    override fun existsById(aLong: Long):Boolean

    override fun findAll() : Iterable<TodoList>

    override fun findAllById(ids: Iterable<Long>): Iterable<TodoList>

    override  fun count(): Long

    override  fun deleteById(id: Long)

    override fun delete(todoList: TodoList)

    override fun deleteAll(todoListIterable:Iterable<TodoList>)

    override fun deleteAll()

}