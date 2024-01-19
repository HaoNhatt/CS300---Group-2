package com.example.movieticket.user.data

import android.util.Log
import com.example.movieticket.staff.data.Theater
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UserFireStoreController {
    private val db = Firebase.firestore

    fun syncMoviesListWithFireStore(moviesList: MutableList<Movie>) {
        val TAG = "Sync movies"

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
        val TAG = "Sync theaters"

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
}