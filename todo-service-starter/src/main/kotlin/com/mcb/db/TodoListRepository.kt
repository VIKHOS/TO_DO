package com.mcb.db

import com.mcb.common.IDataOperations
import com.mcb.common.TodoItem
import com.mcb.common.TodoList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.PreparedStatementCreator
import org.springframework.stereotype.Component
import java.sql.Types
import javax.sql.DataSource
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.jdbc.support.KeyHolder
import java.sql.Connection
import java.sql.PreparedStatement


const val todoListTable = "list"
const val  idField = "id"
const val nameField = "name"

@Component
class TodoListRepository @Autowired constructor(private val dataSource: DataSource) : IDataOperations {


    private final val jdbcTemplate: JdbcTemplate = JdbcTemplate(this.dataSource)



    override fun createTodoList(name: String, items: MutableList<TodoItem>): TodoList
    {
        var todoList = TodoList()
        var keyHolder = GeneratedKeyHolder()

        val insertSql = "INSERT INTO $todoListTable($nameField) VALUES (?);"


        val preparedStatement = object : PreparedStatementCreator {
            override fun createPreparedStatement(it: Connection): PreparedStatement {
                val ps = it.prepareStatement(insertSql)
                ps.setString(1, name)
                return ps
            }
        }

        jdbcTemplate.update(preparedStatement, keyHolder)

        todoList = TodoList(keyHolder.key?.toInt() as Int, name, items)

        return todoList

    }




    override fun addItem(idList: Int, description: String): TodoItem {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fetchAll(): MutableList<TodoList> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteItem(idList: Int, idItem: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateItem(idList: Int, idItem: Int, completeFlag: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}