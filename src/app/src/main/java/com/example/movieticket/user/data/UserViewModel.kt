package com.example.movieticket.user.data

import androidx.lifecycle.ViewModel

//import com.example.movieticket.database.Movie
//import com.example.movieticket.database.Schedule
//import com.example.movieticket.database.Theater
//import com.example.movieticket.database.UserProfile

class UserViewModel : ViewModel() {
    private val dbController = UserFireStoreController()
    private lateinit var user: UserProfile
    var moviesList: MutableList<Movie> = mutableListOf(
        Movie(
            "1",
            "Inside Out 2",
            "New film",
            2023,
            16,
            "Adventure, Funny, Childhood",
            "John Smith",
            "Jame Smith",
            300,
            "english",
            true
        ),
        Movie(
            "2",
            "Inside Out 3",
            "New film 2",
            2023,
            16,
            "Adventure, Funny, Childhood and Comedy",
            "John William",
            "Jame William",
            360,
            "vietnamese",
            true
        ),
        Movie(
            "3",
            "Inside Out 4",
            "New film 3",
            2023,
            16,
            "Adventure, Childhood and Comedy",
            "John Jack",
            "Jame Jack",
            240,
            "india",
            true
        ),
        Movie(
            "4",
            "Inside Out 5",
            "New film 4",
            2023,
            16,
            "Adventure, Funny and Comedy",
            "John Harry",
            "Jame harry",
            270,
            "france",
            true
        ),
        Movie(
            "5",
            "Inside Out 6",
            "New film 5",
            2023,
            16,
            "Adventure, Childhood",
            "John Cena",
            "Jame Cena",
            330,
            "japanese",
            true
        )
    )
    var theaterList: MutableList<Theater> = mutableListOf(
        Theater("1", "CGV Nguyen Hong Dao", "Nguyen Hong Dao P.14 Q.TanBinh TP.HCM"),
        Theater("2", "CGV Nguyen Van Cu", "Nguyen Van Cu P.11 Q.5 TP.HCM"),
        Theater("3", "CGV Nguyen Thi Minh Khai", "Nguyen Thi Minh Khai P.11 Q.5 TP.HCM"),
        Theater("4", "CGV Nguyen Dinh Chieu", "Nguyen Dinh Chieu P.14 Q.5 TP.HCM"),
        Theater("5", "CGV Pasture", "Pasture P.11 Q.1 TP.HCM"),
    )
//    private var schedulesList = mutableListOf<Schedule>()

    private var schedulesList: MutableList<Schedule> = mutableListOf(
        Schedule("1", "1", "1", "11/1", "13h"),
        Schedule("2", "2", "1", "11/1", "15h"),
        Schedule("3", "3", "1", "12/1", "13h"),
        Schedule("4", "4", "2", "12/1", "17h"),
        Schedule("5", "5", "2", "13/1", "15h"),
        Schedule("6", "1", "2", "13/1", "17h"),
        Schedule("7", "2", "3", "14/1", "12h"),
        Schedule("8", "3", "3", "14/1", "14h"),
        Schedule("9", "4", "3", "15/1", "12h"),
        Schedule("10", "5", "4", "16/1", "16h"),
        Schedule("11", "1", "4", "16/1", "16h"),
        Schedule("12", "2", "4", "16/1", "16h"),
        Schedule("13", "3", "5", "16/1", "16h"),
        Schedule("14", "4", "5", "16/1", "16h"),
        Schedule("15", "5", "5", "16/1", "16h"),
    )
    lateinit var seatList: MutableList<Seat>
    var filteredSchedulesList: MutableList<Schedule> = mutableListOf()
    var selectedMovieIndex: Int = -1
    var selectedTheaterIndex: Int = -1
    var selectedScheduleIndex: Int = -1
    var selectedSeatIndex: Int = -1
    var seatSelectedList: MutableList<Seat> = mutableListOf()
    var seatType: SeatType = SeatType.NORMAL
    private var viewSearchResult = false


    fun tryLogin(username: String, password: String, callback: (Int) -> Unit) {
        dbController.getUserAuthInFireStore(username) { result ->
            val (userAuth, isUserExist) = result

            if (isUserExist) {
                if (password == userAuth.password) {
                    callback.invoke(3) // Successful login
                } else {
                    callback.invoke(2) // Incorrect password
                }
            } else {
                callback.invoke(1) // User not found
            }
        }
    }

    fun logIn(username: String) {
        dbController.getUserProfileInFireStore(username) { result ->
            this.user = result
        }
    }

    fun checkUserExist(username: String, callback: (Int) -> Unit) {
        dbController.checkUserAuthExist(username) { result ->
            if (result) {
                callback.invoke(1)
            } else {
                callback.invoke(2)
            }
        }
    }

    fun addUserToRemoteDatabase(username: String, password: String) {
        dbController.addUserToFireStore(username, password)
    }

    fun updateUserInformation() {
        dbController.updateAccountInfoToFireStore(
            this.user.id,
            this.user.name,
            this.user.age,
            this.user.sex,
            this.user.email,
            this.user.phone
        )
    }

//    fun logIn(userProfile: UserProfile) {
//        this.user = userProfile
//    }

//    fun createTempUser() {
//        user = UserProfile("haohuynh255", "hao", 20, true, "haohuynh255@gmail.com", "090090090")
//    }

    fun getUser(): UserProfile {
        return this.user
    }

    fun setUser(newName: String, newAge: Int, newSex: Boolean, newEmail: String, newPhone: String) {
        this.user.name = newName
        this.user.age = newAge
        this.user.sex = newSex
        this.user.email = newEmail
        this.user.phone = newPhone
    }

    fun filterSchedule(selectedMovieID: String) {
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