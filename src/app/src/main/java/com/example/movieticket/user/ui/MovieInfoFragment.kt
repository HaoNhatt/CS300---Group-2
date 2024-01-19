package com.example.movieticket.user.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.movieticket.R
import com.example.movieticket.databinding.FragmentCustomerMovieInfoBinding
import com.example.movieticket.user.data.UserViewModel

class MovieInfoFragment : Fragment() {
    private val viewModel: UserViewModel by activityViewModels()
    private lateinit var binding: FragmentCustomerMovieInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCustomerMovieInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backArrow.setOnClickListener {
            findNavController().navigate(R.id.action_customerMovieInfoFragment_to_customerMainMenuFragment)
        }

        binding.startBookingButton.setOnClickListener {
            findNavController().navigate(R.id.action_customerMovieInfoFragment_to_customerChooseTheaterFragment)
        }

        binding.movieTitle.text = viewModel.moviesList[viewModel.selectedMovieIndex].title
        binding.movieDescription.text = viewModel.moviesList[viewModel.selectedMovieIndex].description
        binding.movieYear.text = viewModel.moviesList[viewModel.selectedMovieIndex].year.toString()
        "${viewModel.moviesList[viewModel.selectedMovieIndex].duration} min".also { binding.movieDuration.text = it }
    }

    companion object
}