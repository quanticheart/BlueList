package com.quanticheart.data.database.utils

import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.quanticheart.core.extentions.system.logE

object FunctionsCoroutine {

    /**
     * Select Query
     */
    inline fun <reified Table> getQuerySelectAll(vararg whereParan: Where): SupportSQLiteQuery {
        var whereBuild = ""
        if (whereParan.isNotEmpty()) {
            val builder = StringBuilder()
            whereParan.forEachIndexed { index, where ->
                builder.append(" ${if (index == 0) " where" else " and"} ${where.column} ${where.operator} ${where.condition}")
            }
            whereBuild = builder.toString()
        }
        return SimpleSQLiteQuery("select * from ${tableName<Table>()}$whereBuild")
    }

    inline fun <reified Table> getQuerySelectByID(
        id: String,
        vararg whereParan: Where
    ): SupportSQLiteQuery {
        var where = ""
        if (whereParan.isNotEmpty()) {
            val builder = StringBuilder()
            whereParan.map {
                builder.append(" and ${it.column} ${it.operator} ${it.condition}")
            }
            where = builder.toString()
        }
        return SimpleSQLiteQuery(
            "select * from ${tableName<Table>()} where _id = $id$where"
        )
    }

    /**
     * Delete Query
     */
    inline fun <reified Table> getQueryDeleteTableByID(
        whereParan: String,
        valueForWhere: String
    ): SupportSQLiteQuery {
        return SimpleSQLiteQuery("delete from ${tableName<Table>()} where $whereParan = $valueForWhere")
    }

    /**
     * Get Table Name
     */
    inline fun <reified Table> tableName(): String = Table::class.java.simpleName
}