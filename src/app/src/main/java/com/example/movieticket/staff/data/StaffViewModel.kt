package com.example.movieticket.staff.data

import android.util.Log
import androidx.lifecycle.ViewModel

class StaffViewModel : ViewModel() {
    lateinit var staff: StaffProfile
        private set
    var selectedTheater: Theater? = null
    lateinit var theatersList: MutableList<Theater>
        private set

    init {
        staff = StaffProfile("name", 20, "sex", "email", "phone", true)
        theatersList = mutableListOf(Theater("Theater 1", "HCMC"),
        Theater("Theater 2", "HCMC"),
        Theater("Theater 3", "HCMC"),
        Theater("Theater 4", "HCMC"))
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

    fun staffLogout() {
//        staff = null
    }
}