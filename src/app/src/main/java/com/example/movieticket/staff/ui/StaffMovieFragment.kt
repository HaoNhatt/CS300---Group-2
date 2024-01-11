package com.example.movieticket.staff.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
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

        Log.d("==========", viewModel.moviesList.size.toString())
    }

}