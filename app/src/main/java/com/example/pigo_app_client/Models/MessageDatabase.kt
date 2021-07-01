package com.example.pigo_app_client.Models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Message::class], version = 1)
abstract class MessageDatabase : RoomDatabase(){
    abstract fun messageDao() : MessageDAO
    companion object{
        private var instance: MessageDatabase? = null

        @Synchronized
        fun getInstance(context: Context): MessageDatabase? {
            if (instance == null) {
                synchronized(MessageDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MessageDatabase::class.java,
                        "msg-database"
                    ).build()
                }
            }
            return instance
        }
    }
}