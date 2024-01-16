package com.example.movieticket.user.data

import androidx.lifecycle.ViewModel
import com.example.movieticket.database.Movie
import com.example.movieticket.database.Schedule
import com.example.movieticket.database.Theater
import com.example.movieticket.database.UserProfile

class UserViewModel : ViewModel() {
    private lateinit var user: UserProfile
    var moviesList: MutableList<Movie> = mutableListOf(
        Movie(1, "Inside Out 2", "New film", 2023, 16, "Adventure, Funny, Childhood", "John Smith", "Jame Smith", "english", true),
        Movie(2, "Inside Out 3", "New film 2", 2023, 16, "Adventure, Funny, Childhood and Comedy", "John William", "Jame William", "vietnamese", true),
        Movie(3, "Inside Out 4", "New film 3", 2023, 16, "Adventure, Childhood and Comedy", "John Jack", "Jame Jack", "india", true),
        Movie(4, "Inside Out 5", "New film 4", 2023, 16, "Adventure, Funny and Comedy", "John Harry", "Jame harry", "france", true),
        Movie(5, "Inside Out 6", "New film 5", 2023, 16, "Adventure, Childhood", "John Cena", "Jame Cena", "japanese", true))
    var theaterList: MutableList<Theater> = mutableListOf(
        Theater(1, "CGV Nguyen Hong Dao", "Nguyen Hong Dao P.14 Q.TanBinh TP.HCM"),
        Theater(2, "CGV Nguyen Van Cu", "Nguyen Van Cu P.11 Q.5 TP.HCM"),
        Theater(3, "CGV Nguyen Thi Minh Khai", "Nguyen Thi Minh Khai P.11 Q.5 TP.HCM"),
        Theater(4, "CGV Nguyen Dinh Chieu", "Nguyen Dinh Chieu P.14 Q.5 TP.HCM"),
        Theater(5, "CGV Pasture", "Pasture P.11 Q.1 TP.HCM"),
        )
    private var schedulesList: MutableList<Schedule> = mutableListOf(
        Schedule(1, "11/1", 1, 1, "13h", "300"),
        Schedule(2, "11/1", 1, 2, "15h", "300"),
        Schedule(3, "12/1", 2, 2, "13h", "240"),
        Schedule(4, "12/1", 2, 3, "17h", "240"),
        Schedule(5, "13/1", 3, 3, "15h", "270"),
        Schedule(6, "13/1", 3, 4, "17h", "270"),
        Schedule(7, "14/1", 4, 4, "12h", "330"),
        Schedule(8, "14/1", 4, 5, "14h", "330"),
        Schedule(9, "15/1", 5, 5, "12h", "360"),
        Schedule(10, "16/1", 5, 1, "16h", "360"))
    var filteredSchedulesList: MutableList<Schedule> = mutableListOf()
    var selectedMovieIndex: Int = -1
    var selectedTheaterIndex: Int = -1
    var selectedScheduleIndex: Int = -1
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

    fun setUser(newName: String, newAge: Int, newSex: Boolean, newEmail: String, newPhone: String, newWallet: Int) {
        this.user.name = newName
        this.user.age = newAge
        this.user.sex = newSex
        this.user.email = newEmail
        this.user.phone = newPhone
        this.user.wallet = newWallet
    }

    fun filterSchedule(selectedMovieID: Int) {
        // Clear data first
        this.filteredSchedulesList.clear()

        // Add new data
        for (schedule in this.schedulesList) {
            if (schedule.movieID == selectedMovieID) {
                filteredSchedulesList.add(schedule)
            }
        }
    }

    fun loadMovies(queryResult: List<Movie>) {
        for (movie in queryResult)
            this.moviesList.add(movie)
    }
}