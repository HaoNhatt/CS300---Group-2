package com.example.movieticket.staff.data

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class StaffViewModelMovie : ViewModel() {
    private val firestore = Firebase.firestore
    lateinit var staff: StaffProfile
        private set
    var selectedMovieIndex: Int = -1
    var moviesList = mutableListOf<Movie>()
        private set

    init {
        staff = StaffProfile("name", 20, "sex", "email", "phone", true)
//        theatersList = mutableListOf(Theater("Theater 1", "HCMC"),
//        Theater("Theater 2", "HCMC"),
//        Theater("Theater 3", "HCMC"),
//        Theater("Theater 4", "HCMC"))
        syncMoviesListwithFirestore()

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

    private fun syncMoviesListwithFirestore() {
        val TAG = "Sync movies"

        firestore.collection("Movies").addSnapshotListener { snapshot, e ->
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
                            val movie = document.toObject(Movie::class.java)
                            if (moviesList.any { t -> t.id == movie.id })
                                continue
                            movie.id = document.id
                            moviesList.add(movie)
                            Log.d("Load theaters", "${document.id} => ${document.data}")
                        }

                        DocumentChange.Type.MODIFIED -> {
                            val document = docChange.document
                            val modifiedTheater = document.toObject(Theater::class.java)
                            val theater = moviesList.find { it.id == document.id }
                            theater?.name = modifiedTheater.name
                            theater?.genre = modifiedTheater.address
                        }

                        DocumentChange.Type.REMOVED -> {
                            val document = docChange.document
                            val removedTheater = document.toObject(Theater::class.java)
                            moviesList.remove(removedMovie)
                        }
                    }
                moviesList.reverse()
            }
        }
    }

    private fun addMovieToFirestore(name: String, genre: String): String {
        val theaterMapping = mapOf(
            "name" to name,
            "genre" to genre
        )
        var addedID = ""
        firestore.collection("Movies")
            .add(movieMapping)
            .addOnSuccessListener { documentReference ->
                addedID = documentReference.id
                Log.d("Add movie", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("Add movie", "Error adding document", e)
            }
        return addedID
    }

    fun addMovie(name: String, address: String) {
        val addedID = addMovieToFirestore(name, address)
        moviesList.add(Movie(name, genre, addedID))
    }

    fun modifyMovie(index: Int, name: String, genre: String) {
        moviesList[index].name = name
        moviesList[index].genre = genre
    }

    fun deleteMovie(index: Int) {
        moviesList.removeAt(index)
        TODO()
//        remove coresponding schedule
    }

    fun staffLogout() {
//        staff = null
    }
}