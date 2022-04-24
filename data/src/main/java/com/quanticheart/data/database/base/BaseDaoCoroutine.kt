package com.quanticheart.data.database.base

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery

interface BaseDaoCoroutine<T> {
    /**
     * raw query for customs selects, deletes or others
     *
     * @param query for room run
     */
    @RawQuery
    suspend fun rawQueryList(query: SupportSQLiteQuery): List<T>?

    @RawQuery
    suspend fun rawQuery(query: SupportSQLiteQuery): T?

    @RawQuery
    suspend fun rawQuerySimple(query: SupportSQLiteQuery): Long

    /**
     * get count from table
     *
     * @param query for count table
     */
    @RawQuery
    suspend fun count(query: SupportSQLiteQuery): Long

    /**
     * Insert an object in the database.
     *
     * @param obj the object to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: List<T>): LongArray

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: ArrayList<T>): LongArray

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg obj: T): LongArray

    /**
     * Update an object from the database.
     *
     * @param obj the object to be updated
     */
    @Update
    suspend fun update(obj: T): Int

    /**
     * Delete an object from the database
     *
     * @param obj the object to be deleted
     */
    @Delete
    suspend fun delete(obj: T): Int

    @RawQuery
    suspend fun deleteAll(query: SupportSQLiteQuery): LongArray

    /**
     * Select all data from table
     *
     * @param query for select in table
     */
    @RawQuery
    suspend fun selectAll(query: SupportSQLiteQuery): List<T>

    @RawQuery
    suspend fun selectByID(query: SupportSQLiteQuery): T
}
