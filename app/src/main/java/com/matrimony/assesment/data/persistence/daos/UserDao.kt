package com.matrimony.assesment.data.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.matrimony.assesment.data.persistence.entity.User

@Dao
interface UserDao {
    @Insert
    suspend fun insertData(data: User)

    @Query("SELECT * from user_table ORDER BY id ASC")
    fun getAllData(): LiveData<List<User>>

    @Query("SELECT COUNT(*) FROM user_table")
    suspend fun getCount(): Int

    @Query("SELECT * FROM user_table WHERE id = :id")
    fun getUser(id: Int): LiveData<User>

}