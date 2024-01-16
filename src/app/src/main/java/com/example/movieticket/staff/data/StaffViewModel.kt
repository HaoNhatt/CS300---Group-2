package com.example.movieticket.staff.data

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.ktx.firestore

class StaffViewModel : ViewModel() {
    val dbController = FirestoreController()
    lateinit var staff: StaffProfile
        private set
    var selectedTheaterIndex: Int = -1
    var selectedMovieIndex: Int = -1
    var theatersList = mutableListOf<Theater>()
        private set
    var moviesList = mutableListOf<Movie>()
        private set

    init {
        staff = StaffProfile("name", 20, "sex", "email", "phone", true)
        dbController.syncTheatersListwithDB(theatersList)
        dbController.syncMoviesListwithDB(moviesList)

    }

    fun tryLogin(username: String, password: String): Boolean {
//        if valid, set the current staff profile return true
        return true

//        if (TODO()) {
//            staff =
//            return true
//        }
//        return false
    }



    fun addTheater(name: String, address: String) {
        val addedID = dbController.addTheaterToDB(name, address)
        theatersList.add(Theater(name, address, addedID))
    }

    fun modifyTheater(index: Int, name: String, address: String) {
        theatersList[index].name = name
        theatersList[index].address = address
        dbController.modifyTheaterInDB(theatersList[index].id, name, address)
    }

    fun deleteTheater(index: Int) {
        dbController.deleteTheaterFromDB(theatersList[index].id)
        theatersList.removeAt(index)
//        remove coresponding schedule
    }



    fun addMovie(title: String, year: String, duration: Int, description: String) {
        val addedID = dbController.addMovieToDB(title, year, duration, description)
        moviesList.add(Movie(title, year, duration, description, addedID))
    }

    fun modifyMovie(index: Int, title: String, year: String, duration: Int, description: String) {
        moviesList[index].title = title
        moviesList[index].year = year
        moviesList[index].duration = duration
        moviesList[index].description = description
        dbController.modifyMovieInDB(moviesList[index].id, title, year, duration, description)
    }

    fun deleteMovie(index: Int) {
        dbController.deleteMovieFromDB(moviesList[index].id)
        moviesList.removeAt(index)
    }

    fun staffLogout() {
//        staff = null
    }
}