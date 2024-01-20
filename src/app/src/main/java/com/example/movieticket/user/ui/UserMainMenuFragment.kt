package com.example.movieticket.user.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.movieticket.R
import com.example.movieticket.databinding.FragmentCustomerMainMenuBinding
import com.example.movieticket.user.data.UserViewModel

class UserMainMenuFragment : Fragment() {

    companion object;

    private val viewModel: UserViewModel by activityViewModels()
    private lateinit var binding: FragmentCustomerMainMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCustomerMainMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.selectedMovieIndex = 0
        binding.movieTitle.text = viewModel.moviesList[viewModel.selectedMovieIndex].title
        binding.movieDescription.text = viewModel.moviesList[viewModel.selectedMovieIndex].description

        binding.leftArrowButton.setOnClickListener {
            if (viewModel.selectedMovieIndex == 0) {
                viewModel.selectedMovieIndex = viewModel.moviesList.size - 1
            } else {
                viewModel.selectedMovieIndex -= 1
            }
            binding.movieTitle.text = viewModel.moviesList[viewModel.selectedMovieIndex].title
            binding.movieDescription.text = viewModel.moviesList[viewModel.selectedMovieIndex].description
        }

        binding.rightArrowButton.setOnClickListener {
            if (viewModel.selectedMovieIndex == (viewModel.moviesList.size - 1)) {
                viewModel.selectedMovieIndex = 0
            } else {
                viewModel.selectedMovieIndex += 1
            }
            binding.movieTitle.text = viewModel.moviesList[viewModel.selectedMovieIndex].title
            binding.movieDescription.text = viewModel.moviesList[viewModel.selectedMovieIndex].description
        }

        binding.customerAccountIcon.setOnClickListener {
            findNavController().navigate(R.id.action_customerMainMenuFragment_to_customerAccountInfoFragment)
        }

        binding.customerBookedHistoryIcon.setOnClickListener {
            findNavController().navigate(R.id.action_customerMainMenuFragment_to_customerBookedHistoryFragment)
        }

        binding.groupNowPlaying.setOnClickListener {
            findNavController().navigate(R.id.action_customerMainMenuFragment_to_nowPlayingFragment)
        }

        binding.viewDetailButton.setOnClickListener {
            findNavController().navigate(R.id.action_customerMainMenuFragment_to_customerMovieInfoFragment)
        }
    }
}