package com.example.movieticket.user.data

import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
    private val dbController = UserFireStoreController()

    private lateinit var user: UserProfile

    var moviesList = mutableListOf<Movie>()

    //    var moviesList: MutableList<Movie> = mutableListOf(
//        Movie(
//            "1",
//            "Inside Out 2",
//            "New film",
//            2023,
//            300,
//        ),
//        Movie(
//            "2",
//            "Inside Out 3",
//            "New film 2",
//            2023,
//            360,
//        ),
//        Movie(
//            "3",
//            "Inside Out 4",
//            "New film 3",
//            2023,
//            240,
//        ),
//        Movie(
//            "4",
//            "Inside Out 5",
//            "New film 4",
//            2023,
//            270,
//        ),
//        Movie(
//            "5",
//            "Inside Out 6",
//            "New film 5",
//            2023,
//            330,
//        )
//    )
    var theatersList = mutableListOf<Theater>()

    //    var theaterList: MutableList<Theater> = mutableListOf(
//        Theater("1", "CGV Nguyen Hong Dao", "Nguyen Hong Dao P.14 Q.TanBinh TP.HCM"),
//        Theater("2", "CGV Nguyen Van Cu", "Nguyen Van Cu P.11 Q.5 TP.HCM"),
//        Theater("3", "CGV Nguyen Thi Minh Khai", "Nguyen Thi Minh Khai P.11 Q.5 TP.HCM"),
//        Theater("4", "CGV Nguyen Dinh Chieu", "Nguyen Dinh Chieu P.14 Q.5 TP.HCM"),
//        Theater("5", "CGV Pasture", "Pasture P.11 Q.1 TP.HCM"),
//    )
    var schedulesList = mutableListOf<Schedule>()

    //    private var schedulesList: MutableList<Schedule> = mutableListOf(
//        Schedule("1", "1", "1", "11/1", "13h"),
//        Schedule("2", "2", "1", "11/1", "15h"),
//        Schedule("3", "3", "1", "12/1", "13h"),
//        Schedule("4", "4", "2", "12/1", "17h"),
//        Schedule("5", "5", "2", "13/1", "15h"),
//        Schedule("6", "1", "2", "13/1", "17h"),
//        Schedule("7", "2", "3", "14/1", "12h"),
//        Schedule("8", "3", "3", "14/1", "14h"),
//        Schedule("9", "4", "3", "15/1", "12h"),
//        Schedule("10", "5", "4", "16/1", "16h"),
//        Schedule("11", "1", "4", "16/1", "16h"),
//        Schedule("12", "2", "4", "16/1", "16h"),
//        Schedule("13", "3", "5", "16/1", "16h"),
//        Schedule("14", "4", "5", "16/1", "16h"),
//        Schedule("15", "5", "5", "16/1", "16h"),
//    )
//    var ticketsList = mutableListOf<Ticket>()
    var userTicketsList = mutableListOf<Ticket>()

    // Seat that current customer want to select
    var seatSelectingList: MutableSet<String> = mutableSetOf()

    // Type of the selecting seat, can only select all Normal or all VIP type
    var seatType: SeatType = SeatType.NORMAL
    val NORMALSEATTYPE: Int = 100
    val VIPSEATTYPE: Int = 120
    var totalSeatCost: Int = 0

    // Seat that being selected already
    var seatSelectedList: MutableSet<String> = mutableSetOf()

    var filteredSchedulesList: MutableList<Schedule> = mutableListOf()

    var selectedMovieIndex: Int = -1
    var selectedTheaterIndex: Int = -1
    var selectedScheduleIndex: Int = -1
    var selectedSeatIndex: Int = -1
    var selectedTicketHistory: Int = -1

    private var viewSearchResult = false

    init {
        dbController.syncMoviesListWithFireStore(moviesList)
        dbController.syncTheatersListWithFireStore(theatersList)
        dbController.syncSchedulesListWithFireStore(schedulesList)
//        dbController.syncTicketsListWithFireStore(ticketsList)
    }

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
                callback.invoke(1) // Username already exist, inform to choose another username
            } else {
                callback.invoke(2) // Username can be used
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

    fun exportTicket() {
        val addedID = dbController.addTicketToFireStore(
            user.username,
            schedulesList[selectedScheduleIndex].id,
            seatSelectingList,
            totalSeatCost,
        )
        var seatListEdited = ""
        for (seat in seatSelectingList) {
            seatListEdited += seat
            if (seatSelectingList.last() != seat) {
                seatListEdited += ", "
            }
        }
//        ticketsList.add(Ticket(addedID, user.username, schedulesList[selectedScheduleIndex].id, seatListEdited, totalSeatCost))
        userTicketsList.add(Ticket(addedID, user.username, schedulesList[selectedScheduleIndex].id, seatListEdited, totalSeatCost))
    }

    fun filterSeat(scheduleID: String, callback: (Int) -> Unit) {
        dbController.getTicketFromFireStoreByScheduleID(scheduleID) { result ->
            for (ticket in result) {
//                if (schedulesList[selectedScheduleIndex].id == ticket.scheduleID) {
//                    val seatBooked = ticket.seatList.split(",")
//                    val editedSeatBooked = seatBooked.map { it.trim() }
//                    seatSelectedList.addAll(editedSeatBooked)
//                    Log.d("HaoNhat", seatSelectedList.toString())
//                }

//                seatSelectedList.add(ticket.toString())
//                Log.d("HaoNhat", seatSelectedList.toString())

                val seatBooked = ticket.seatList.split(",")
                val editedSeatBooked = seatBooked.map { it.trim() }
                seatSelectedList.addAll(editedSeatBooked)
            }
            callback.invoke(1)
        }
    }

    fun getUserBookedHistory(callback: (Int) -> Unit) {
        dbController.getTicketFromFireStoreByUsername(user.username) { result ->
            userTicketsList = result
            callback.invoke(1)
        }
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

    fun filterSchedule(selectedMovieID: String, selectedTheaterID: String) {
        // Clear data first
        this.filteredSchedulesList.clear()

        // Add new data
        for (schedule in this.schedulesList) {
            if (schedule.movieID == selectedMovieID && schedule.theaterID == selectedTheaterID) {
                filteredSchedulesList.add(schedule)
            }
        }
    }

    fun loadMovies(queryResult: List<Movie>) {
        for (movie in queryResult)
            this.moviesList.add(movie)
    }
}