package com.example.movieticket.user.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.movieticket.R
import com.example.movieticket.databinding.FragmentCustomerLoginBinding
import com.example.movieticket.databinding.FragmentCustomerMainMenuBinding
import com.example.movieticket.databinding.FragmentCustomerMovieInfoBinding
import com.example.movieticket.user.data.UserViewModel

class MovieInfoFragment : Fragment() {
    private val viewModel: UserViewModel by activityViewModels()
    private lateinit var binding: FragmentCustomerMovieInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

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
            findNavController().navigate(R.id.action_customerMovieInfoFragment_to_customerFinishPaymentFragment)
        }

        binding.movieTitle.text = viewModel.moviesList[viewModel.selectedMovieIndex].title
        binding.movieDescription.text = viewModel.moviesList[viewModel.selectedMovieIndex].description
        binding.movieYear.text = viewModel.moviesList[viewModel.selectedMovieIndex].year.toString()
        binding.movieAge.text = viewModel.moviesList[viewModel.selectedMovieIndex].age.toString()
        binding.movieDirector.text = viewModel.moviesList[viewModel.selectedMovieIndex].director
        binding.movieActor.text = viewModel.moviesList[viewModel.selectedMovieIndex].actors
        binding.movieLanguage.text = viewModel.moviesList[viewModel.selectedMovieIndex].language
    }

    companion object {

    }
}