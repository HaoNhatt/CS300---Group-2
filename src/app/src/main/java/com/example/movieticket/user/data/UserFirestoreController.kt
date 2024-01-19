package com.example.movieticket.user.data

import android.util.Log
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UserFireStoreController {
    private val db = Firebase.firestore

    fun syncMoviesListWithFireStore(moviesList: MutableList<Movie>) {
        val TAG = "HaoNhat"

        db.collection("Movies").addSnapshotListener { snapshot, e ->
            // exception
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                return@addSnapshotListener
            }
            // listen on where the data is changed
            val source = if (snapshot != null && snapshot.metadata.hasPendingWrites()) {
                "Local"
            } else {
                "Server"
            }

            val changes = snapshot?.documentChanges
            if (changes != null && source == "Server") {
                for (docChange in changes)
                    when (docChange.type) {
                        DocumentChange.Type.ADDED -> {
                            val document = docChange.document
                            val movie = document.toObject(Movie::class.java)
                            movie.id = document.id
                            moviesList.add(movie)
                            Log.d("$TAG - ADDED", "${document.id} => ${document.data}")
                        }

                        DocumentChange.Type.MODIFIED -> {
                            val document = docChange.document
                            val modifiedMovie = document.toObject(Movie::class.java)
                            modifiedMovie.id = document.id
                            val index = moviesList.indexOfFirst { it.id == document.id }
                            moviesList[index] = modifiedMovie
                            Log.d("$TAG - MODIFIED", "${document.id} => ${document.data}")
                        }

                        DocumentChange.Type.REMOVED -> {
                            val document = docChange.document
                            val removedMovie = document.toObject(Movie::class.java)
                            removedMovie.id
                            moviesList.remove(removedMovie)
                            Log.d("$TAG - REMOVED", "${document.id} => ${document.data}")
                        }
                    }
                moviesList.reverse()
            }
        }
    }

    fun syncTheatersListWithFireStore(theatersList: MutableList<Theater>) {
        val TAG = "HaoNhat"

        db.collection("Theaters").addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                return@addSnapshotListener
            }
            val source = if (snapshot != null && snapshot.metadata.hasPendingWrites()) {
                "Local"
            } else {
                "Server"
            }

            val changes = snapshot?.documentChanges
            if (changes != null && source == "Server") {
                for (docChange in changes)
                    when (docChange.type) {
                        DocumentChange.Type.ADDED -> {
                            val document = docChange.document
                            val theater = document.toObject(Theater::class.java)
                            theater.id = document.id
                            theatersList.add(theater)
                            Log.d("$TAG - ADDED", "${document.id} => ${document.data}")
                        }

                        DocumentChange.Type.MODIFIED -> {
                            val document = docChange.document
                            val modifiedTheater = document.toObject(Theater::class.java)
                            modifiedTheater.id = document.id
                            val index = theatersList.indexOfFirst { it.id == document.id }
                            theatersList[index] = modifiedTheater
                            Log.d("$TAG - MODIFIED", "${document.id} => ${document.data}")
                        }

                        DocumentChange.Type.REMOVED -> {
                            val document = docChange.document
                            val removedTheater = document.toObject(Theater::class.java)
                            removedTheater.id
                            theatersList.remove(removedTheater)
                            Log.d("$TAG - REMOVED", "${document.id} => ${document.data}")
                        }
                    }
                theatersList.reverse()
            }
        }
    }

    fun syncSchedulesListWithFireStore(schedulesList: MutableList<Schedule>) {
        val TAG = "HaoNhat"

        db.collection("Schedules").addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                return@addSnapshotListener
            }
            val source = if (snapshot != null && snapshot.metadata.hasPendingWrites()) {
                "Local"
            } else {
                "Server"
            }

            val changes = snapshot?.documentChanges
            if (changes != null && source == "Server") {
                for (docChange in changes)
                    when (docChange.type) {
                        DocumentChange.Type.ADDED -> {
                            val document = docChange.document
                            val schedule = document.toObject(Schedule::class.java)
                            schedule.id = document.id
                            schedulesList.add(schedule)
                            Log.d("$TAG - ADDED", "${document.id} => ${document.data}")
                        }

                        DocumentChange.Type.MODIFIED -> {
                            val document = docChange.document
                            val modifiedSchedule = document.toObject(Schedule::class.java)
                            modifiedSchedule.id = document.id
                            val index = schedulesList.indexOfFirst { it.id == document.id }
                            schedulesList[index] = modifiedSchedule
                            Log.d("$TAG - MODIFIED", "${document.id} => ${document.data}")
                        }

                        DocumentChange.Type.REMOVED -> {
                            val document = docChange.document
                            val removedSchedule = document.toObject(Schedule::class.java)
                            removedSchedule.id
                            schedulesList.remove(removedSchedule)
                            Log.d("$TAG - REMOVED", "${document.id} => ${document.data}")
                        }
                    }
                schedulesList.reverse()
            }
        }
    }

    fun syncTicketsListWithFireStore(ticketsList: MutableList<Ticket>) {
        val TAG = "HaoNhat"

        db.collection("Tickets").addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                return@addSnapshotListener
            }
            val source = if (snapshot != null && snapshot.metadata.hasPendingWrites()) {
                "Local"
            } else {
                "Server"
            }

            val changes = snapshot?.documentChanges
            if (changes != null && source == "Server") {
                for (docChange in changes)
                    when (docChange.type) {
                        DocumentChange.Type.ADDED -> {
                            val document = docChange.document
                            val ticket = document.toObject(Ticket::class.java)
                            ticket.id = document.id
                            ticketsList.add(ticket)
                            Log.d("$TAG - ADDED", "${document.id} => ${document.data}")
                        }

                        DocumentChange.Type.MODIFIED -> {
                            val document = docChange.document
                            val modifiedTicket = document.toObject(Ticket::class.java)
                            modifiedTicket.id = document.id
                            val index = ticketsList.indexOfFirst { it.id == document.id }
                            ticketsList[index] = modifiedTicket
                            Log.d("$TAG - MODIFIED", "${document.id} => ${document.data}")
                        }

                        DocumentChange.Type.REMOVED -> {
                            val document = docChange.document
                            val removedTicket = document.toObject(Ticket::class.java)
                            removedTicket.id
                            ticketsList.remove(removedTicket)
                            Log.d("$TAG - REMOVED", "${document.id} => ${document.data}")
                        }
                    }
                ticketsList.reverse()
            }
        }
    }

    fun getUserAuthInFireStore(
        username: String,
        callback: (Pair<UserAuthInfo, Boolean>) -> Unit
    ) {
        var userAuth: UserAuthInfo? = null
        var isUserExist: Boolean = false

        db.collection("UserAuthInfo").whereEqualTo("username", username).get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    userAuth = UserAuthInfo(
                        document.id,
                        document.data["username"].toString(),
                        document.data["password"].toString()
                    )
                    isUserExist = true
                }
                callback.invoke(Pair(userAuth ?: UserAuthInfo("", "", ""), isUserExist))
            }
            .addOnFailureListener { exception ->
                Log.w("HaoNhat", "Error getting documents: ", exception)
                callback.invoke(Pair(UserAuthInfo("", "", ""), false))
            }
    }

    fun getUserProfileInFireStore(
        username: String,
        callback: (UserProfile) -> Unit
    ) {
        var userProfile: UserProfile? = null

        db.collection("UserProfiles").whereEqualTo("username", username).get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    userProfile = UserProfile(
                        document.id,
                        document.data["username"].toString(),
                        document.data["name"].toString(),
                        document.data["age"].toString().toInt(),
                        document.data["sex"].toString().toBoolean(),
                        document.data["email"].toString(),
                        document.data["phone"].toString(),
                    )
                }
                callback.invoke(userProfile ?: UserProfile("", "", "", 0, false, "", ""))
            }
            .addOnFailureListener { exception ->
                Log.w("HaoNhat", "Error getting documents: ", exception)
                callback.invoke(UserProfile("", "", "", 0, false, "", ""))
            }
    }

    fun checkUserAuthExist(
        username: String,
        callback: (Boolean) -> Unit
    ) {
        var isUserExist: Boolean = false

        db.collection("UserAuthInfo").whereEqualTo("username", username).get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    isUserExist = true
                }
                callback.invoke(isUserExist)
            }
            .addOnFailureListener { exception ->
                Log.w("HaoNhat", "Error getting documents: ", exception)
                callback.invoke(isUserExist)
            }
    }

    fun addUserToFireStore(
        username: String,
        password: String,
    ) {
        val TAG = "HaoNhat"
        val userAuthMapping = mapOf(
            "username" to username,
            "password" to password,
        )
        db.collection("UserAuthInfo")
            .add(userAuthMapping)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }

        val userProfileMapping = mapOf(
            "username" to username,
            "name" to "unnamed",
            "age" to 12,
            "sex" to false,
            "email" to "",
            "phone" to "",
        )
        db.collection("UserProfiles")
            .add(userProfileMapping)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

    fun updateAccountInfoToFireStore(
        id: String,
        name: String,
        age: Int,
        sex: Boolean,
        email: String,
        phone: String
    ) {
        db.collection("UserProfiles").document(id)
            .update(
                "name", name,
                "age", age,
                "sex", sex,
                "email", email,
                "phone", phone
            )
    }

    fun addTicketToFireStore(
        username: String,
        scheduleID: String,
        seatList: MutableSet<String>,
        price: Int,
    ): String {
        val TAG = "HaoNhat"
        var seatListEdited = ""
        for (seat in seatList) {
            seatListEdited += seat
            if (seatList.last() != seat) {
                seatListEdited += ", "
            }
        }
        val ticketMapping = mapOf(
            "username" to username,
            "scheduleID" to scheduleID,
            "seatList" to seatListEdited,
            "price" to price,
        )
        var addedID = ""
        db.collection("Tickets")
            .add(ticketMapping)
            .addOnSuccessListener { documentReference ->
                addedID = documentReference.id
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
        return addedID
    }
}