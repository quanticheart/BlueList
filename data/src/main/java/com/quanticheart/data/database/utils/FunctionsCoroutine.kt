package com.quanticheart.data.database.utils

import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery

object FunctionsCoroutine {

    /**
     * Select Query
     */
    inline fun <reified Table> getQuerySelectAll(): SupportSQLiteQuery {
        return SimpleSQLiteQuery("select * from ${tableName<Table>()}")
    }

    inline fun <reified Table> getQuerySelectByID(id: String): SupportSQLiteQuery {
        return SimpleSQLiteQuery("select * from ${tableName<Table>()} where _id = $id")
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