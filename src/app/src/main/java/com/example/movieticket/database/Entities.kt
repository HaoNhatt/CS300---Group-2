package com.example.movieticket.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "userAuth")
data class UserAuth(
    @PrimaryKey @NonNull @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "password") val password: String,
    @ColumnInfo(name = "id") val id: Int,
)

@Entity(tableName = "staffAuth")
data class StaffAuth(
    @PrimaryKey @NonNull @ColumnInfo(name = "staffName") val staffName: String,
    @ColumnInfo(name = "password") val password: String,
    @ColumnInfo(name = "id") val id: Int,
)

@Entity(tableName = "userProfile")
data class UserProfile(
    @PrimaryKey @NonNull @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "age") val age: Int,
    @ColumnInfo(name = "sex") val sex: Boolean,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "phone") val phone: String,
    @ColumnInfo(name = "id") val id: Int,
)

@Entity(tableName = "staffProfile")
data class StaffProfile(
    @PrimaryKey @NonNull @ColumnInfo(name = "staffName") val staffName: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "age") val age: Int,
    @ColumnInfo(name = "sex") val sex: Boolean,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "phone") val phone: String,
    @ColumnInfo(name = "isManager") val isManager: Boolean,
    @ColumnInfo(name = "id") val id: Int,
)

@Entity(tableName = "movie")
data class Movie(
    @PrimaryKey(autoGenerate = true) @NonNull val uid: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "year") val year: Int,
)

@Entity(tableName = "theater")
data class Theater(
    @PrimaryKey(autoGenerate = true) @NonNull val uid: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "address") val address: String,
)

@Entity(tableName = "schedule", foreignKeys = [ForeignKey(entity = Movie::class, parentColumns = ["uid"], childColumns = ["movieID"], onDelete = ForeignKey.CASCADE)])
data class Schedule(
    @PrimaryKey(autoGenerate = true) @NonNull val uid: Int,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "movieID") val movieID: String,
    @ColumnInfo(name = "startingTime") val startTime: String,
    @ColumnInfo(name = "endingTime") val endTime: String,
)