package com.mcb.restmodel

import javax.persistence.*

@Entity
class TodoList (var name: String){


    constructor(name: String, items: List<TodoItem>): this(name)

    @Id
    @GeneratedValue
    var id:Long=0

    @OneToMany(cascade= arrayOf(CascadeType.ALL),orphanRemoval = true, fetch = FetchType.EAGER)
    var items: List<TodoItem>? = null
}