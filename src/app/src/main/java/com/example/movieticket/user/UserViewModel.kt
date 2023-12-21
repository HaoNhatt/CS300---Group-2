package com.example.movieticket.user

import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModel
import com.example.movieticket.database.AppDatabase
import com.example.movieticket.database.Movie
import com.example.movieticket.database.UserProfile
import com.example.movieticket.database.UserProfileDao
import com.example.movieticket.staff.data.Theaters

class UserViewModel : ViewModel() {
    private lateinit var user: UserProfile
    private lateinit var userProfileDao: UserProfileDao
    private lateinit var moviesList: MutableList<Movie>
    private lateinit var selectedMovie: Movie
    private var viewSearchResult = false

    fun logIn(userProfile: UserProfile) {
        this.user = userProfile
    }

    fun createUser() {
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