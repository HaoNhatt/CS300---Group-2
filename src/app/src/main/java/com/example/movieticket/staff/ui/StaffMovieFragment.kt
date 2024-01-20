package com.example.movieticket.staff.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieticket.R
import com.example.movieticket.databinding.FragmentStaffMovieBinding
import com.example.movieticket.databinding.FragmentStaffTheatersBinding
import com.example.movieticket.staff.data.StaffViewModel


class StaffMovieFragment : Fragment() {

    private val viewModel: StaffViewModel by activityViewModels()
    private lateinit var binding: FragmentStaffMovieBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentStaffMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addMovieButton.setOnClickListener {
            findNavController().navigate(R.id.action_staffMovieFragment_to_addMovieFragment)
        }

        val moviesRecyclerView = binding.moviesRecyclerView
        val adapter = StaffMoviesAdapter(viewModel)
        moviesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        moviesRecyclerView.adapter = adapter
    }

}


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
            it.findNavController().navigate(R.id.action_staffMovieFragment_to_movieDetailFragment)
        }
    }
}