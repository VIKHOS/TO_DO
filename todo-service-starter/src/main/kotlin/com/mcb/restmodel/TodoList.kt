package com.mcb.restmodel

import javax.persistence.*

@Entity
class TodoList constructor(var name: String){

    @Id
    @GeneratedValue
    var id:Long=0

    @OneToMany(cascade= arrayOf(CascadeType.ALL),orphanRemoval = true, fetch = FetchType.EAGER)
    var items: List<TodoItem>? = null
}