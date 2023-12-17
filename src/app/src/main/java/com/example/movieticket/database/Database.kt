package com.example.movieticket.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserAuth::class, StaffAuth::class, UserProfile::class, StaffProfile::class, Movie::class, Theater::class, Schedule::class,],
    version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserAuthDao
    abstract fun staffDao(): StaffAuthDao
    abstract fun userProfileDao(): UserProfileDao
    abstract fun staffProfileDao(): StaffProfileDao
    abstract fun movieDao(): MovieDao
    abstract fun theaterDao(): TheaterDao
    abstract fun scheduleDao(): ScheduleDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null)
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(context, AppDatabase::class.java, "DogDB").build()
                }

            return INSTANCE!!
        }
    }
}