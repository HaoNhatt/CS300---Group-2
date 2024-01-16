package com.example.movieticket.staff.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.movieticket.R
import com.example.movieticket.databinding.FragmentAddMovieBinding
import com.example.movieticket.databinding.FragmentAddTheaterBinding
import com.example.movieticket.staff.data.StaffViewModel

class AddMovieFragment : Fragment() {
    private val viewModel: StaffViewModel by activityViewModels()
    private lateinit var binding: FragmentAddMovieBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentAddMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addMovieButton2.setOnClickListener {
            val title = binding.movieTitleEditText.text.toString()
            val year = binding.movieYearEditText.text.toString()
            val duration = Integer.parseInt(binding.movieDurationEditText.text.toString())
            val description = binding.movieDescriptionEditText.text.toString()
            viewModel.addMovie(title, year, duration, description)
            findNavController().navigate(R.id.action_addMovieFragment_to_staffMovieFragment)
        }
    }

}
