package com.example.movieticket.staff.data

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class StaffViewModel : ViewModel() {
    private val firestore = Firebase.firestore
    lateinit var staff: StaffProfile
        private set
    var selectedTheaterIndex: Int = -1
    var theatersList = mutableListOf<Theater>()
        private set

    init {
        staff = StaffProfile("name", 20, "sex", "email", "phone", true)
//        theatersList = mutableListOf(Theater("Theater 1", "HCMC"),
//        Theater("Theater 2", "HCMC"),
//        Theater("Theater 3", "HCMC"),
//        Theater("Theater 4", "HCMC"))
        syncTheatersListwithFirestore()

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

    private fun syncTheatersListwithFirestore() {
        val TAG = "Sync theaters"

        firestore.collection("Theaters").addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                return@addSnapshotListener
            }

            val changes = snapshot?.documentChanges
            if (changes != null) {
                for (docChange in changes)
                    when (docChange.type) {
                        DocumentChange.Type.ADDED -> {
                            val document = docChange.document
                            val theater = document.toObject(Theater::class.java)
                            if (theatersList.any { t -> t.id == theater.id })
                                continue
                            theater.id = document.id
                            theatersList.add(theater)
                            Log.d("Load theaters", "${document.id} => ${document.data}")
                        }

                        DocumentChange.Type.MODIFIED -> {
                            val document = docChange.document
                            val modifiedTheater = document.toObject(Theater::class.java)
                            val theater = theatersList.find { it.id == document.id }
                            theater?.name = modifiedTheater.name
                            theater?.address = modifiedTheater.address
                        }

                        DocumentChange.Type.REMOVED -> {
                            val document = docChange.document
                            val removedTheater = document.toObject(Theater::class.java)
                            theatersList.remove(removedTheater)
                        }
                    }
                theatersList.reverse()
            }
        }
    }

    private fun addTheaterToFirestore(name: String, address: String): String {
        val theaterMapping = mapOf(
            "name" to name,
            "address" to address
        )
        var addedID = ""
        firestore.collection("Theaters")
            .add(theaterMapping)
            .addOnSuccessListener { documentReference ->
                addedID = documentReference.id
                Log.d("Add theater", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("Add theater", "Error adding document", e)
            }
        return addedID
    }

    fun addTheater(name: String, address: String) {
        val addedID = addTheaterToFirestore(name, address)
        theatersList.add(Theater(name, address, addedID))
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