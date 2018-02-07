package com.artur.sms

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context

/**
 * Created by Artur on 1/18/2018.
 */
@Database(version = 2,entities = arrayOf(ContactDto::class, ContactListDto::class))
@TypeConverters(DbTypeConverters::class)
abstract class AppDatabase:RoomDatabase() {
    abstract fun contactDao(): ContactDao

    companion object {
        val DB_NAME = "sms-osp-database"
        private var dbInstance: AppDatabase? = null

        fun getDatabase(context: Context) : AppDatabase {
            if (dbInstance == null) {
                dbInstance = Room.databaseBuilder<AppDatabase>(context.applicationContext, AppDatabase::class.java, DB_NAME).fallbackToDestructiveMigration().build()
            }
            return dbInstance!!
        }
    }
}