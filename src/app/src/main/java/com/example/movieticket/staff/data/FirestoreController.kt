package com.example.movieticket.staff.data

import android.util.Log
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirestoreController {
    private val firestore = Firebase.firestore

    fun syncTheatersListwithDB(theatersList: MutableList<Theater>) {
        val TAG = "Sync theaters"

        firestore.collection("Theaters").addSnapshotListener { snapshot, e ->
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

    fun addTheaterToDB(name: String, address: String): String {
        val TAG = "Add theater"
        val theaterMapping = mapOf(
            "name" to name,
            "address" to address
        )
        var addedID = ""
        firestore.collection("Theaters")
            .add(theaterMapping)
            .addOnSuccessListener { documentReference ->
                addedID = documentReference.id
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
        return addedID
    }

    fun modifyTheaterInDB(id: String, name: String, address: String) {
        firestore.collection("Theaters").document(id)
            .update("name", name,
        "address", address)
    }

    fun deleteTheaterFromDB(id: String) {
        val TAG = "Delete theater"
        firestore.collection("Theaters").document(id)
            .delete()
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully deleted!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }
    }


    fun syncMoviesListwithDB(moviesList: MutableList<Movie>) {
        val TAG = "Sync movies"

        firestore.collection("Movies").addSnapshotListener { snapshot, e ->
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

    fun addMovieToDB(title: String, year: String, duration: Int, description: String): String {
        val TAG = "Add movie"
        val movieMapping = mapOf(
            "title" to title,
            "year" to year,
            "duration" to duration,
            "description" to description
        )
        var addedID = ""
        firestore.collection("Movies")
            .add(movieMapping)
            .addOnSuccessListener { documentReference ->
                addedID = documentReference.id
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
        return addedID
    }

    fun modifyMovieInDB(id: String, title: String, year: String, duration: Int, description: String) {
        firestore.collection("Movies").document(id)
            .update("title", title,
        "year", year,
                        "duration", duration,
                        "description", description)
    }

    fun deleteMovieFromDB(id: String) {
        val TAG = "Delete movie"
        firestore.collection("Movies").document(id)
            .delete()
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully deleted!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }
    }
}