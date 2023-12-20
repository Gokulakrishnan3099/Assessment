package com.matrimony.assesment.data.persistence

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.matrimony.assesment.data.persistence.daos.UserDao
import com.matrimony.assesment.data.persistence.entity.User

@Database(entities = [User::class], version = 1, exportSchema = false)
public abstract class MyDatabase: RoomDatabase() {
    public abstract fun UserDao(): UserDao

    companion object{
        @Volatile
        private var instance: MyDatabase? = null

        @Synchronized
        fun getInstance(applicationContext: Application): MyDatabase {
            if(instance == null) {
                instance = Room.databaseBuilder(
                    applicationContext.applicationContext,
                    MyDatabase::class.java,
                    "my_database.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance!!
        }
    }
}