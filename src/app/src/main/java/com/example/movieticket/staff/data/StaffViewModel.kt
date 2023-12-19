package com.example.movieticket.staff.data

import android.util.Log
import androidx.lifecycle.ViewModel

class StaffViewModel : ViewModel() {
    lateinit var staff: StaffProfile
        private set
    lateinit var theatersList: MutableList<Theaters>
        private set

    init {
        staff = StaffProfile("name", 20, "sex", "email", "phone", true)
        theatersList = mutableListOf(Theaters("Theater 1", "HCMC"),
        Theaters("Theater 2", "HCMC"),
        Theaters("Theater 3", "HCMC"),
        Theaters("Theater 4", "HCMC"))
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