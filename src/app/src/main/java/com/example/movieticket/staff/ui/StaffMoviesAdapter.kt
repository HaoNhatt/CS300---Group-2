package com.example.movieticket.staff.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieticket.R
import com.example.movieticket.staff.data.StaffViewModel

class StaffMoviesAdapter(private val viewModel: StaffViewModel): RecyclerView.Adapter<StaffMoviesAdapter.MovieViewHolder>() {

    class MovieViewHolder(private val view:View) : RecyclerView.ViewHolder(view) {
        val movieNameView = view.findViewById<TextView>(R.id.textViewItem)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.textview_item, parent, false)

        return MovieViewHolder(layout)
    }

    override fun getItemCount(): Int = viewModel.moviesList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.movieNameView.text = viewModel.moviesList[position].title
        holder.itemView.setOnClickListener {
            viewModel.selectedMovieIndex = position
//            it.findNavController().navigate(R.id.action_staffTheatersFragment_to_theaterDetailFragment)
        }
    }
}