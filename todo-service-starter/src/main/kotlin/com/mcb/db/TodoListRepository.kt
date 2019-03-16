package com.mcb.db

import com.mcb.common.IDataOperations
import com.mcb.common.TodoList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.PreparedStatementCreator
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.sql.Connection
import java.sql.PreparedStatement
import javax.print.attribute.IntegerSyntax
import javax.sql.DataSource



@Component
class TodoListRepository @Autowired constructor(private val dataSource: DataSource,
                                                private val todoItemRepository: TodoItemRepository)  {

    val todoListTable = "list"
    val  idField = "id"
    val nameField = "name"

    private final val jdbcTemplate: JdbcTemplate = JdbcTemplate(this.dataSource)

    fun create(name: String) : Int
    {
        var result = -1;
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

        result = keyHolder.key?.toInt()!!

        return result
    }

    fun selectAll() : MutableList<TodoList>
    {
        var listTdList = mutableListOf<TodoList>()

        val query = "SELECT * FROM $todoListTable;"

        val rows = jdbcTemplate.queryForList(query)
        for (row in rows)
        {
            val id = (row[idField] as BigDecimal).intValueExact()
            val name = row[nameField] as String
            val items = todoItemRepository.selectListItems(id)
            listTdList.add(TodoList(id, name, items))
        }

        return listTdList
    }
}