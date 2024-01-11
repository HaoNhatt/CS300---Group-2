package com.example.movieticket.user.data

import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.example.movieticket.database.AppDatabase
import com.example.movieticket.database.Movie
import com.example.movieticket.database.Schedule
import com.example.movieticket.database.UserProfile
import com.example.movieticket.database.UserProfileDao
import com.example.movieticket.staff.data.Theater
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private lateinit var user: UserProfile
    private lateinit var moviesList: MutableList<Movie>
    private lateinit var selectedMovie: Movie
    private lateinit var selectedSchedule: Schedule
    private var viewSearchResult = false


    fun logIn(userProfile: UserProfile) {
        this.user = userProfile
    }

    fun createTempUser() {
        user = UserProfile("haohuynh255", "hao", 20, true, "haohuynh255@gmail.com", "090090090")
    }

    fun getUser(): UserProfile {
        return this.user
    }

    fun loadMovies(queryResult: List<Movie>) {
        for (movie in queryResult)
            this.moviesList.add(movie)
    }
}