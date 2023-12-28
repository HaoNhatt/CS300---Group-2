package com.example.movieticket.staff.data

data class StaffAuthInfo(var username: String,
                         var password: String)

data class StaffProfile(var name: String,
                        var age: Int,
                        var sex: String,
                        var email: String,
                        var phone: String,
                        var isManager: Boolean)

data class Theater(var name: String,
                    var address: String)