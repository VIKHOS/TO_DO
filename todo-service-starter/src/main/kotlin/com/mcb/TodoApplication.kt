package mcb.todo

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
@EnableAutoConfiguration
class TodoApplication
    fun main(args: Array<String>) {
        try{
            runApplication<TodoApplication>(*args)
        }
        catch(e: Exception){
            e.printStackTrace()
        }
    }


