package com.example.movieticket.staff.data

import androidx.lifecycle.ViewModel

class StaffViewModel : ViewModel() {
    private var staff: StaffProfile? = null

    fun tryLogin(username: String, password: String): Boolean {
//        if valid, set the current staff profile return true

        staff = StaffProfile("name", 20, "sex", "email", "phone", true)
        return true

//        if (TODO()) {
//            staff =
//            return true
//        }
//        return false
    }

    fun staffLogout() {
        staff = null
    }
}