
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


    /*@Autowired
    val restTemplate = TestRestTemplate()

    val rootUrl = "http://localhost:8083"


    @Test
    fun `Create a new todolist`()
    {
        val newTodoListName = "Work"
        val responseEntity = restTemplate.postForEntity<TodoList>("$rootUrl/todos/$newTodoListName")
        Assert.assertEquals(HttpStatus.CREATED, responseEntity.statusCode)
        Assert.assertEquals(newTodoListName, responseEntity.body?.name)
    }

    /*@Test
    fun `Creating a new todolist which name already exists`()
    {
        val newTodoListName = "Work"

        //first creation
        val respEntFirstStep = restTemplate.postForEntity<TodoList>("$rootUrl/todos/$newTodoListName")

        //second creation
        val respEntSecondStep = restTemplate.postForEntity<TodoResponse>("$rootUrl/todos/$newTodoListName")

        Assert.assertEquals(HttpStatus.BAD_REQUEST, respEntSecondStep.statusCode)
        Assert.assertEquals(ALREADY_EXISTS, respEntSecondStep.body?.responseMsg)

    }*/

    @Test
    fun `given a blank name, create a new todolist`()
    {
        val newTodoListName = ""
        val responseEntity = restTemplate.postForEntity<TodoResponse>("$rootUrl/todos/$newTodoListName")
        Assert.assertEquals(HttpStatus.METHOD_NOT_ALLOWED, responseEntity.statusCode)
    }*/


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



}

