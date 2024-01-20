package com.example.movieticket.user.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.movieticket.R
import com.example.movieticket.user.data.UserViewModel

class CustomerMovieAdapter(private val viewModel: UserViewModel): RecyclerView.Adapter<CustomerMovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val movieBannerView = view.findViewById<ImageView>(R.id.movieItemBanner)!!
        val movieNameView = view.findViewById<TextView>(R.id.movieItemTitle)!!
        val movieDescriptionView = view.findViewById<TextView>(R.id.movieItemDescription)!!

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.list_movie_item, parent, false)

        return MovieViewHolder(layout)
    }

    override fun getItemCount(): Int = viewModel.filteredMoviesList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
//        holder.movieBannerView.setImageResource()
        holder.movieNameView.text = viewModel.filteredMoviesList[position].title
        holder.movieDescriptionView.text = viewModel.filteredMoviesList[position].description
        holder.itemView.setOnClickListener {
            viewModel.selectedMovieIndex = position
            it.findNavController().navigate(R.id.action_nowPlayingFragment_to_customerMovieInfoFragment)
        }
    }
}