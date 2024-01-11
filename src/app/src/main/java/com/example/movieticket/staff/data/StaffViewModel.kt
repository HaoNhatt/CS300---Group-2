package com.example.movieticket.staff.data

import android.util.Log
import androidx.lifecycle.ViewModel

class StaffViewModel : ViewModel() {
    lateinit var staff: StaffProfile
        private set
    var selectedTheaterIndex: Int = -1
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

    fun addTheater(name: String, address: String) {
        theatersList.add(Theater(name, address))
        Log.d("============", theatersList.size.toString())
    }

    fun modifyTheater(index: Int, name: String, address: String) {
        theatersList[index].name = name
        theatersList[index].address = address
    }

    fun deleteTheater(index: Int) {
        theatersList.removeAt(index)
        TODO()
//        remove coresponding schedule
    }

    fun staffLogout() {
//        staff = null
    }
}