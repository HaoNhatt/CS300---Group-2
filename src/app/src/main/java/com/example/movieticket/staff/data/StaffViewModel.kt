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
    var selectedMovieIndex: Int = -1
    var theatersList = mutableListOf<Theater>()
        private set
    var moviesList = mutableListOf<Movie>()
        private set

    init {
        staff = StaffProfile("name", 20, "sex", "email", "phone", true)
//        theatersList = mutableListOf(Theater("Theater 1", "HCMC"),
//        Theater("Theater 2", "HCMC"),
//        Theater("Theater 3", "HCMC"),
//        Theater("Theater 4", "HCMC"))
        syncTheatersListwithFirestore()
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
                            if (!theatersList.any { t -> t.id == removedTheater.id })
                                continue
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

    private fun modifyTheaterInFirestore(id: String, name: String, address: String) {
        firestore.collection("Theaters").document(id)
            .update("name", name)
        firestore.collection("Theaters").document(id)
            .update("address", address)
    }

    fun modifyTheater(index: Int, name: String, address: String) {
        theatersList[index].name = name
        theatersList[index].address = address
        modifyTheaterInFirestore(theatersList[index].id, name, address)
    }

    fun deleteTheaterFromFirestore(id: String) {
        val TAG = "Delete theater"
        firestore.collection("Theaters").document(id)
            .delete()
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully deleted!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }
    }

    fun deleteTheater(index: Int) {
        deleteTheaterFromFirestore(theatersList[index].id)
        theatersList.removeAt(index)
//        remove coresponding schedule
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
                            val modifiedMovie = document.toObject(Movie::class.java)
                            val movie = moviesList.find { it.id == document.id }
                            movie?.title = modifiedMovie.title
                            movie?.year = modifiedMovie.year
                            movie?.description = modifiedMovie.description
                        }

                        DocumentChange.Type.REMOVED -> {
                            val document = docChange.document
                            val removedMovie = document.toObject(Movie::class.java)
                            if (!moviesList.any { m -> m.id == removedMovie.id })
                                continue
                            moviesList.remove(removedMovie)
                        }
                    }
                moviesList.reverse()
            }
        }
    }

    private fun addMovieToFirestore(title: String, year: String, description: String): String {
        val movieMapping = mapOf(
            "title" to title,
            "year" to year,
            "description" to description
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

    fun addMovie(title: String, year: String, description: String) {
        val addedID = addMovieToFirestore(title, year, description)
        moviesList.add(Movie(title, year, description, addedID))
    }

    private fun modifyMovieInFirestore(id: String, title: String, year: String, description: String) {
        firestore.collection("Movies").document(id)
            .update("title", title)
        firestore.collection("Movies").document(id)
            .update("year", year)
        firestore.collection("Movies").document(id)
            .update("description", description)
    }

    fun modifyMovie(index: Int, title: String, year: String, description: String) {
        moviesList[index].title = title
        moviesList[index].year = year
        moviesList[index].description = description
        modifyMovieInFirestore(moviesList[index].id, title, year, description)
    }

    fun deleteMovieFromFirestore(id: String) {
        val TAG = "Delete movie"
        firestore.collection("Movies").document(id)
            .delete()
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully deleted!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }
    }

    fun deleteMovie(index: Int) {
        deleteMovieFromFirestore(moviesList[index].id)
        moviesList.removeAt(index)
    }

    fun staffLogout() {
//        staff = null
    }
}