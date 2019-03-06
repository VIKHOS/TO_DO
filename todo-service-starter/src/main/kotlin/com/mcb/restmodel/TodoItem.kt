package com.mcb.restmodel

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class TodoItem constructor()
{
    @Id
    @GeneratedValue
    var id: Int = 0
    var description: String = ""
    var completed: Boolean = false

    constructor(desc: String): this()
    {
        description = desc
    }


}

