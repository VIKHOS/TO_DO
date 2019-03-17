package com.mcb.db

import com.mcb.common.TodoItem
import com.mcb.common.TodoList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.PreparedStatementCreator
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.sql.Connection
import java.sql.PreparedStatement
import javax.sql.DataSource


@Component
class TodoItemRepository @Autowired constructor(private val dataSource: DataSource) {

    val todoItemTable = "item"
    val idField = "id"
    val descField = "description"
    val completedField = "completed"
    val listIdField = "listId"

    private final val jdbcTemplate: JdbcTemplate = JdbcTemplate(this.dataSource)

   fun create(description: String, listId: Int, completed: Boolean = false) : Int
    {
        var result = -1;
        var keyHolder = GeneratedKeyHolder()

        val insertSql = "INSERT INTO $todoItemTable($descField, $completedField, $listIdField) VALUES (?, ?, ?);"


        val preparedStatement = object : PreparedStatementCreator {
            override fun createPreparedStatement(it: Connection): PreparedStatement {
                val ps = it.prepareStatement(insertSql)
                ps.setString(1, description)
                ps.setInt(2, (if (completed) 1 else 0))
                ps.setInt(3, listId)
                return ps
            }
        }

        jdbcTemplate.update(preparedStatement, keyHolder)

        result = keyHolder.key?.toInt()!!

        return result
    }

    fun selectListItems(idList: Int) : MutableList<TodoItem>
    {
        var listItems = mutableListOf<TodoItem>()

        val query = "SELECT * FROM $todoItemTable where $listIdField = $idList;"

        val rows = jdbcTemplate.queryForList(query)
        for (row in rows)
        {
            val id = row[idField] as BigDecimal
            val desc = row[descField] as String
            val completedIntValue = row[completedField] as Short
            val completed = completedIntValue.toInt() == 1
            listItems.add(TodoItem(id.intValueExact(), desc, completed))
        }

        return listItems
    }

    fun deleteItem(idItem: Int, idList: Int): Int {

        val query = "DELETE FROM $todoItemTable WHERE $idField = $idItem" +
                                                "AND $listIdField = $idList"

        val preparedStatement = PreparedStatementCreator { connection -> connection.prepareStatement(query)  }

        return jdbcTemplate.update(preparedStatement)
    }


    fun updateItem(idItem: Int, idList: Int, completed: Boolean): Int {

        val query = "UPDATE  $todoItemTable " +
                "SET $completedField = ?  " +
                "WHERE $idField = $idItem" +
                "AND $listIdField = $idList"

        val preparedStatement = object : PreparedStatementCreator {
            override fun createPreparedStatement(it: Connection): PreparedStatement {
                val ps = it.prepareStatement(query)
                ps.setInt(1, (if (completed) 1 else 0))
                return ps
            }
        }

        return jdbcTemplate.update(preparedStatement)
    }

}