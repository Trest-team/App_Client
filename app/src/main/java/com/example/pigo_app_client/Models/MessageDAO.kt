package com.example.pigo_app_client.Models

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MessageDAO {
    @Insert
    fun insert(msg: Message)

    @Query("SELECT * FROM MESSAGE WHERE room = :roomName")
    fun getChat(roomName : String): List<Message>
}