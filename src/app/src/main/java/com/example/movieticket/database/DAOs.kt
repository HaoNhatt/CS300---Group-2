package com.example.movieticket.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserAuthDao {
    @Insert
    suspend fun insert(user: UserAuth)

    @Query("SELECT * FROM userAuth")
    suspend fun getAll(): List<UserAuth>

    @Query("SELECT * FROM userAuth WHERE username = :username")
    suspend fun searchByUsername(username: String): List<UserAuth>
}

@Dao
interface StaffAuthDao {
    @Insert
    suspend fun insert(staff: StaffAuth)

    @Query("SELECT * FROM staffauth")
    suspend fun getAll(): List<StaffAuth>
}

@Dao
interface UserProfileDao {
    @Insert
    suspend fun insert(userProfile: UserProfile)

    @Query("SELECT * FROM userProfile")
    suspend fun getAll(): List<UserProfile>

    @Query("SELECT * FROM userProfile WHERE username = :username")
    suspend fun searchByUsername(username: String): List<UserProfile>

    @Query("UPDATE userProfile SET name = (:newName), age = (:newAge), sex = (:newSex), email = (:newEmail), phone = (:newPhone) WHERE username = (:newUsername)")
    suspend fun updateUser(
        newUsername: String,
        newName: String,
        newAge: Int,
        newSex: Boolean,
        newEmail: String,
        newPhone: String
    )
}

@Dao
interface StaffProfileDao {
    @Insert
    suspend fun insert(staffProfile: StaffProfile)

    @Query("SELECT * FROM staffProfile")
    suspend fun getAll(): List<StaffProfile>
}

@Dao
interface MovieDao {
    @Insert
    suspend fun insert(movie: Movie)

    @Delete
    suspend fun delete(movie: Movie)

    @Query("DELETE FROM movie WHERE 1=1")
    suspend fun deleteAll()

    @Query("SELECT * FROM movie")
    suspend fun getAll(): List<Movie>

    @Query("SELECT * FROM movie WHERE nowPlaying = 1")
    suspend fun getAllNowPlaying(): List<Movie>

    @Query("SELECT * FROM movie WHERE title like '%' || :movieName || '%'")
    suspend fun searchByName(movieName: String): List<Movie>

//    @Query("UPDATE movie SET imagePath = (:imagePath), name = (:dogName), breed = (:dogBreed), description = (:dogDescription) WHERE uid = (:imageID)")
//    suspend fun updateMovie(imageID: Int, imagePath: String, dogName: String, dogBreed: String, dogDescription: String)
//
//    @Query("SELECT * FROM movie WHERE breed like '%' || :keywords || '%'")
//    suspend fun searchByBreed(keywords: String): List<Movie>
//
//    @Query("SELECT * FROM movie WHERE description like '%' || :keywords || '%'")
//    suspend fun searchByDescription(keywords: String): List<Movie>
}

@Dao
interface TheaterDao {
    @Insert
    suspend fun insert(theater: Theater)

    @Query("SELECT * FROM theater")
    suspend fun getAll(): List<Theater>
}

@Dao
interface ScheduleDao {
    @Insert
    suspend fun insert(schedule: Schedule)

    @Query("SELECT * FROM schedule")
    suspend fun getAll(): List<Schedule>

    @Query("SELECT * FROM schedule WHERE movieID = :movieID")
    suspend fun getScheduleOfMovie(movieID: Int): List<Schedule>
}

@Dao
interface InvoiceDao {
    @Insert
    suspend fun insert(invoice: Invoice)

    @Query("SELECT * FROM invoice")
    suspend fun getAll(): List<Invoice>

    @Query("SELECT * FROM invoice WHERE username = :username")
    suspend fun getInvoiceOfUser(username: String): List<Invoice>
}