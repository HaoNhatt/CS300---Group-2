package com.example.movieticket.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "userAuth")
data class UserAuth(
    @PrimaryKey @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "password") val password: String,
)

@Entity(tableName = "staffAuth")
data class StaffAuth(
    @PrimaryKey @ColumnInfo(name = "staffName") val staffName: String,
    @ColumnInfo(name = "password") val password: String,
)

@Entity(tableName = "userProfile")
data class UserProfile(
    @PrimaryKey @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "name") var name: String = "",
    @ColumnInfo(name = "age") var age: Int = 0,
    @ColumnInfo(name = "sex") var sex: Boolean = false,
    @ColumnInfo(name = "email") var email: String = "",
    @ColumnInfo(name = "phone") var phone: String = "",
    @ColumnInfo(name = "wallet") var wallet: Int = 0,
)

@Entity(tableName = "staffProfile")
data class StaffProfile(
    @PrimaryKey @ColumnInfo(name = "staffName") val staffName: String,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "age") var age: Int,
    @ColumnInfo(name = "sex") var sex: Boolean,
    @ColumnInfo(name = "email") var email: String,
    @ColumnInfo(name = "phone") var phone: String,
    @ColumnInfo(name = "isManager") val isManager: Boolean,
)

@Entity(tableName = "movie")
data class Movie(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "description") var description: String = "",
    @ColumnInfo(name = "year") var year: Int = 0,
    @ColumnInfo(name = "target_age") var age: Int = 0,
    @ColumnInfo(name = "genre") var genre: String = "",
    @ColumnInfo(name = "director") var director: String = "",
    @ColumnInfo(name = "actors") var actors: String = "",
    @ColumnInfo(name = "language") var language: String = "",
    @ColumnInfo(name = "nowPlaying") var nowPlaying: Boolean = false,
)

@Entity(tableName = "theater")
data class Theater(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "address") var address: String,
)

@Entity(tableName = "schedule", foreignKeys = [ForeignKey(entity = Movie::class, parentColumns = ["uid"], childColumns = ["movieID"], onDelete = ForeignKey.CASCADE), ForeignKey(entity = Theater::class, parentColumns = ["uid"], childColumns = ["theaterID"], onDelete = ForeignKey.CASCADE)])
data class Schedule(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "date") var date: String,
    @ColumnInfo(name = "movieID") var movieID: Int,
    @ColumnInfo(name = "theaterID") var theaterID: Int,
    @ColumnInfo(name = "startingTime") var startTime: String,
    @ColumnInfo(name = "duration") var duration: String,
)

@Entity(tableName = "invoice", foreignKeys = [ForeignKey(entity = UserProfile::class, parentColumns = ["username"], childColumns = ["username"], onDelete = ForeignKey.CASCADE), ForeignKey(entity = Schedule::class, parentColumns = ["uid"], childColumns = ["scheduleID"], onDelete = ForeignKey.CASCADE)])
data class Invoice(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "username") var username: String,
    @ColumnInfo(name = "scheduleID") var scheduleID: Int,
)