package com.example.movieticket.staff.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.movieticket.R
import com.example.movieticket.databinding.FragmentAddTheaterBinding
import com.example.movieticket.databinding.FragmentMovieDetailBinding
import com.example.movieticket.databinding.FragmentTheaterDetailBinding
import com.example.movieticket.staff.data.StaffViewModel


class MovieDetailFragment : Fragment() {
    private val viewModel: StaffViewModel by activityViewModels()
    private lateinit var binding: FragmentMovieDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val selectedMovie = viewModel.moviesList[viewModel.selectedMovieIndex]
        binding.movieTitleDetailEditText.setText(selectedMovie.title)
        binding.movieYearDetailEditText.setText(selectedMovie.year)
        binding.movieDescriptionDetailEditText.setText(selectedMovie.description)

        binding.saveChangesMovieDetailButton.setOnClickListener {
            val title = binding.movieTitleDetailEditText.text.toString()
            val year = binding.movieYearDetailEditText.text.toString()
            val duration = binding.movieDurationDetailEditText.text.toString().toInt()
            val description = binding.movieDescriptionDetailEditText.text.toString()
            viewModel.modifyMovie(viewModel.selectedMovieIndex, title, year, duration, description)
        }

        binding.deleteMovieButton.setOnClickListener {
            viewModel.deleteMovie(viewModel.selectedMovieIndex)
            findNavController().navigate(R.id.action_movieDetailFragment_to_staffMovieFragment)
        }
    }
}