package com.example.pigo_app_client.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Message (val name : String, val msg : String, val room: String,var viewType : Int){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}
