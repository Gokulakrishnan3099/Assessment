package com.matrimony.assesment.data.persistence.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "image")
    val image: String,
    @ColumnInfo(name = "age")
    val age: Int,
    @ColumnInfo(name = "height")
    val height: String,
    @ColumnInfo(name = "language")
    val language: String,
    @ColumnInfo(name = "community")
    val community: String,
    @ColumnInfo(name = "job")
    val job: String,
    @ColumnInfo(name = "address")
    val address: String,
)