package com.mcb.restmodel

import javax.persistence.*

@Entity
class TodoList constructor(){

    constructor(name: String): this()
    {
        this.name = name
    }

    constructor(name: String, items: List<TodoItem>): this(name)
    {
        this.items = items
    }

    @Id
    @GeneratedValue
    var id:Long=0

    var name = ""

    @OneToMany(cascade= arrayOf(CascadeType.ALL),orphanRemoval = true, fetch = FetchType.EAGER)
    var items: List<TodoItem>? = null
}