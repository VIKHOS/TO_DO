
import com.mcb.common.*
import com.mcb.inmemory.TodoManagement
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert
import org.junit.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate

import org.springframework.boot.test.web.client.postForEntity
import org.springframework.http.HttpStatus


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class TodoOperationsTest()
{

    @Test
    fun `Create new todo list`()
    {
        val todoManagement = TodoManagement()
        val result = todoManagement.createTodoList("Work", mutableListOf())

        var expectedResult = TodoList(1, "Work", mutableListOf())

        var listTdList = todoManagement.fetchAll()
        Assert.assertEquals(1 , listTdList.size)
        Assert.assertEquals(expectedResult, listTdList.first())
        Assert.assertEquals(expectedResult, result)
    }

    @Test(expected = TodoException::class)
    fun `given null name, create new todo list`()
    {
        val todoManagement = TodoManagement()
        todoManagement.createTodoList("", mutableListOf())
    }


    @Test(expected = TodoException::class)
    fun `Create todo list with an existing name`()
    {
        val todoManagement = TodoManagement()
        val resultFirstStep = todoManagement.createTodoList("Work", mutableListOf())
        val resultSecondStep = todoManagement.createTodoList("Work", mutableListOf())

    }

    @Test
    fun `Create several todo lists`()
    {
        val todoManagement = TodoManagement()
        val resultFirstStep = todoManagement.createTodoList("Work", mutableListOf())
        val resultSecondStep = todoManagement.createTodoList("Personal", mutableListOf())

        val expResFirstStep = TodoList(1, "Work", mutableListOf())
        val expResSecondStep = TodoList(2, "Personal", mutableListOf())

        val listTdList = todoManagement.fetchAll()
        Assert.assertEquals(2 , listTdList.size)
        Assert.assertEquals(expResFirstStep , resultFirstStep)
        Assert.assertEquals(expResSecondStep , resultSecondStep)

    }

    //test to create an item
    @Test
    fun `Add item to existing list`(){
        val todoManagement = TodoManagement()
        val toDoList= todoManagement.createTodoList("Work", mutableListOf())
        val toDoItemDescription="Piece of word"

        todoManagement.addItem(toDoList.id,toDoItemDescription)
        val listTdList = todoManagement.fetchAll()
        Assert.assertEquals(listTdList.first().items.first().description,toDoItemDescription)
    }

    //test with blank description
    @Test(expected = TodoException::class)
    fun `Given blank description add item to list`(){
        val todoManagement = TodoManagement()
        val toDoList= todoManagement.createTodoList("Work", mutableListOf())
        val toDoItemDescription=""

        todoManagement.addItem(toDoList.id,toDoItemDescription)

    }
    //add item to a list which does not exist
    @Test(expected =  TodoException::class)
    fun `Add item to a non existing list`(){
        val todoManagement = TodoManagement()
        val toDoItemDescription="some item description"

        todoManagement.addItem(1,toDoItemDescription)
    }
    //add several item to the same list
    @Test
    fun `Add several item to same list`(){
        val todoManagement= TodoManagement()
        val todoList=todoManagement.createTodoList("Work", mutableListOf())

        todoManagement.addItem(todoList.id,"item description1")
        todoManagement.addItem(todoList.id,"item description2")

        val listTdList =todoManagement.fetchAll()
        Assert.assertEquals(listTdList.first().items.first().description,"item description1")
        Assert.assertEquals(listTdList.first().items[1].description,"item description2")
    }



}

