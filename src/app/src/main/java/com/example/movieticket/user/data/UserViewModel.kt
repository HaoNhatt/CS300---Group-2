package com.example.movieticket.user.data

import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.example.movieticket.database.AppDatabase
import com.example.movieticket.database.Movie
import com.example.movieticket.database.Schedule
import com.example.movieticket.database.UserProfile
import com.example.movieticket.database.UserProfileDao
import com.example.movieticket.staff.data.StaffProfile
import com.example.movieticket.staff.data.Theater
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private lateinit var user: UserProfile
    lateinit var moviesList: MutableList<Movie>
    var selectedMovieIndex: Int = -1
    private lateinit var selectedMovie: Movie
    private lateinit var selectedSchedule: Schedule
    private var viewSearchResult = false

    init {
        moviesList = mutableListOf(Movie(1, "Inside Out 2", "New film", 2023, 16, "Adventure, Funny, Childhood", "John Smith", "Jame Smith", "english", true),
            Movie(2, "Inside Out 3", "New film 2", 2023, 16, "Adventure, Funny, Childhood and Comedy", "John William", "Jame William", "vietnamese", true),
            Movie(3, "Inside Out 4", "New film 3", 2023, 16, "Adventure, Childhood and Comedy", "John Jack", "Jame Jack", "india", true),
            Movie(4, "Inside Out 5", "New film 4", 2023, 16, "Adventure, Funny and Comedy", "John Harry", "Jame harry", "france", true),
            Movie(5, "Inside Out 6", "New film 5", 2023, 16, "Adventure, Childhood", "John Cena", "Jame Cena", "japanese", true))
    }


    fun logIn(userProfile: UserProfile) {
        this.user = userProfile
    }

    fun createTempUser() {
        user = UserProfile("haohuynh255", "hao", 20, true, "haohuynh255@gmail.com", "090090090")
    }

    fun getUser(): UserProfile {
        return this.user
    }

    fun setUser(newName: String, newAge: Int, newSex: Boolean, newEmail: String, newPhone: String, newWallet: Int) {
        this.user.name = newName
        this.user.age = newAge
        this.user.sex = newSex
        this.user.email = newEmail
        this.user.phone = newPhone
        this.user.wallet = newWallet
    }

    fun loadMovies(queryResult: List<Movie>) {
        for (movie in queryResult)
            this.moviesList.add(movie)
    }
}