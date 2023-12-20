package com.matrimony.assesment.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.matrimony.assesment.data.persistence.MyDatabase
import com.matrimony.assesment.data.persistence.entity.User

class DataRepository(private val application: Application) {

    private val db: MyDatabase by lazy {
        MyDatabase.getInstance(application)
    }
    private val userDao = db.UserDao()

    private val allUser: LiveData<List<User>> by lazy {
        userDao.getAllData()
    }

    suspend fun insert(user: User) {
        userDao.insertData(user)
    }
    fun list(): LiveData<List<User>> {
        return allUser
    }
    suspend fun count(): Int {
        return userDao.getCount()
    }
    fun getUser(id: Int): LiveData<User> {
        return userDao.getUser(id)
    }
}