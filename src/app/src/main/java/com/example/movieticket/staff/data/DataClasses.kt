package com.example.movieticket.staff.data

data class StaffAuthInfo(var username: String,
                         var password: String)

data class StaffProfile(var name: String,
                        var age: Int,
                        var sex: String,
                        var email: String,
                        var phone: String,
                        var isManager: Boolean)

data class Theater(var name: String = "",
                   var address: String = "",
                   var id: String = "0")

data class Movie(var title: String = "",
                 var year: String = "",
                 var duration: Int = 0,
                 var description: String = "",
                 var id: String = "0")

data class Schedule(var id: String = "0",
                    var movieID: String = "0",
                    var theaterID: String = "0",
                    var date: String = "",
                    var startTime: String = "0",)