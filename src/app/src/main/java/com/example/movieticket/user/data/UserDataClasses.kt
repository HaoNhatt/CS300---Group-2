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
    var year: String = "",
    var duration: Int = 0,
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

enum class SeatType {
    NORMAL,
    VIP,
}

data class Ticket(
    var id: String = "0",
    var username: String = "",
    var scheduleID: String = "0",
    var seatList: String = "",
    var price: Int = 0,
)