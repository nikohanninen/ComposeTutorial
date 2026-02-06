package com.example.composetutorial.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(account: Account)

    @Update
    suspend fun update(account: Account)

    @Query("SELECT * from account WHERE id = :id")
    fun getAccount(id: Int): Flow<Account>

    @Query("SELECT * from account")
    fun getAllAccounts(): Flow<List<Account>>

     @Query("DELETE FROM account")
     suspend fun clearAccounts()
}