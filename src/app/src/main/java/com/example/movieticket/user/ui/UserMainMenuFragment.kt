package com.example.movieticket.user.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.movieticket.R
import com.example.movieticket.database.MovieDao
import com.example.movieticket.databinding.FragmentCustomerMainMenuBinding
import com.example.movieticket.user.data.UserViewModel

class UserMainMenuFragment : Fragment() {

    companion object;

    private lateinit var viewModel: UserViewModel
    private lateinit var binding: FragmentCustomerMainMenuBinding
    private lateinit var movieDao: MovieDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(this)[UserViewModel::class.java]
        binding = FragmentCustomerMainMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.customerAccountIcon.setOnClickListener {
            findNavController().navigate(R.id.action_customerMainMenuFragment_to_customerAccountInfoFragment)
        }

        binding.viewDetailButton.setOnClickListener {
            findNavController().navigate(R.id.action_customerMainMenuFragment_to_customerMovieInfoFragment)
        }
    }
}