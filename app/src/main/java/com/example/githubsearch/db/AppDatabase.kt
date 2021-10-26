package com.example.githubsearch.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.githubsearch.db.dao.UserDao
import com.example.githubsearch.model.entity.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}