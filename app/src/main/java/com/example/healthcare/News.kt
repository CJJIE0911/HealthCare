package com.example.healthcare

import androidx.room.Entity
import java.util.*
@Entity(tableName ="news")
data class News (val id:Int, val imageUrl: String, val title: String, val description: String, val author: String, val publishedTime: String){

}