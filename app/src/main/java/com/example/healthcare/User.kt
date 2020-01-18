package com.example.healthcare

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="user")
data class User(@PrimaryKey val username: String,
                val password: String,
                val email: String,
                val gender: Char,
                val weight: Float,
                val height: Float){
}