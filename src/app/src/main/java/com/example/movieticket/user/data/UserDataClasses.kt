package com.example.movieticket.user.data

data class UserAuthInfo(
    var id: String,
    var username: String,
    var password: String,
)

data class UserProfile(
    var id: String,
    var username: String,
    var name: String,
    var age: Int,
    var sex: Boolean,
    var email: String,
    var phone: String,
)

data class Movie(
    var id: String = "0",
    var title: String = "",
    var description: String = "",
    var year: Int = 0,
    var targetAge: Int = 0,
    var genre: String = "",
    var director: String = "",
    var actors: String = "",
    var duration: Int = 0,
    var language: String = "",
    var nowPlaying: Boolean = false,
)

data class Theater(
    var id: String = "0",
    var name: String = "",
    var address: String = "",
)

data class Schedule(
    var id: String = "0",
    var movieID: String = "0",
    var theaterID: String = "0",
    var date: String = "",
    var startTime: String = "0",
)

data class Seat(
    var id: String = "0",
    var scheduleID: String = "0",
    var row: Int = 0,
    var col: Int = 0,
    var isBooked: Boolean = false,
)

enum class SeatStatus {
    AVAILABLE,
    BOOKED,
    SELECTED
}

enum class SeatType {
    NORMAL,
    VIP,
}

data class Invoice(
    var id: Int = 0,
    var username: String = "",
    var scheduleID: Int = 0,
    var seatList: MutableList<Seat>,
)