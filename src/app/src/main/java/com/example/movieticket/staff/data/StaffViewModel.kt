package com.example.movieticket.staff.data

import android.util.Log
import androidx.lifecycle.ViewModel
import java.util.Calendar

class StaffViewModel : ViewModel() {
    private val dbController = FirestoreController()
    var todayDate: Calendar = Calendar.getInstance()
    var selectedDate: Calendar = Calendar.getInstance()
    lateinit var staff: StaffProfile
        private set
    var selectedTheaterIndex: Int = -1
    var selectedMovieIndex: Int = -1
    var theatersList = mutableListOf<Theater>()
        private set
    var moviesList = mutableListOf<Movie>()
        private set
    var schedulesList = mutableListOf<Schedule>()
        private set

    init {
        staff = StaffProfile("name", 20, "sex", "email", "phone", true)
        dbController.syncTheatersListwithDB(theatersList)
        dbController.syncMoviesListwithDB(moviesList)
        dbController.syncScheduleListwithDB(schedulesList)
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



    fun addTheater(name: String, address: String, callback: (Boolean) -> Unit) {
        dbController.addTheaterToDB(name, address) {
            addedID -> theatersList.add(Theater(name, address, addedID))
            callback.invoke(true)
        }
    }

    fun modifyTheater(index: Int, name: String, address: String) {
        theatersList[index].name = name
        theatersList[index].address = address
        Log.d("===Modifying Theater", "Theater ID: ${theatersList[index].id}")
        dbController.modifyTheaterInDB(theatersList[index].id, name, address)
    }

    fun deleteTheater(index: Int) {
        schedulesList.removeIf { it.theaterID ==  theatersList[index].id}

        dbController.deleteTheaterFromDB(theatersList[index].id)
        theatersList.removeAt(index)
    }



    fun addMovie(title: String, year: String, duration: Int, description: String, callback: (Boolean) -> Unit) {
        dbController.addMovieToDB(title, year, duration, description) {
            addedID -> moviesList.add(Movie(title, year, duration, description, addedID))
            callback.invoke(true)
        }
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




    fun addSchedule(movieID: String, theaterID: String, date: String, startTime: String, callback: (Boolean) -> Unit) {
        dbController.addScheduleToDB(movieID, theaterID, date, startTime) {
            addedID -> schedulesList.add(Schedule(movieID, theaterID, date, startTime, addedID))
            callback.invoke(true)
        }
    }

    fun modifySchedule(id: String, movieID: String, theaterID: String, date: String, startTime: String) {
        val index = schedulesList.indexOfFirst { it.id == id }
        schedulesList[index] = Schedule(movieID, theaterID, date, startTime, id)
        dbController.modifyScheduleInDB(movieID, theaterID, date, startTime, id)
    }

    fun deleteSchedule(id: String) {
        val index = schedulesList.indexOfFirst { it.id == id }
        schedulesList.removeAt(index)
        dbController.deleteScheduleFromDB(id)
    }
    fun staffLogout() {
//        staff = null
    }
}