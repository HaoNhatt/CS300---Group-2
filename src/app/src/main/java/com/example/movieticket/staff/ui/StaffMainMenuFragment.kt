package com.example.movieticket.staff.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.movieticket.R
import com.example.movieticket.databinding.FragmentStaffMainMenuBinding
import com.example.movieticket.staff.data.StaffViewModel

class StaffMainMenuFragment : Fragment() {

    companion object {
        fun newInstance() = StaffMainMenuFragment()
    }

    private val viewModel: StaffViewModel by activityViewModels()
    private lateinit var binding: FragmentStaffMainMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentStaffMainMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.accountsButton.setOnClickListener {
            findNavController().navigate(R.id.action_staffMainMenuFragment_to_accountsManagementFragment)
        }
        binding.accountsTextView.setOnClickListener {
            findNavController().navigate(R.id.action_staffMainMenuFragment_to_accountsManagementFragment)
        }

        binding.theatersButton.setOnClickListener {
            findNavController().navigate(R.id.action_staffMainMenuFragment_to_staffTheatersFragment)
        }
        binding.theatersTextView.setOnClickListener {
            findNavController().navigate(R.id.action_staffMainMenuFragment_to_staffTheatersFragment)
        }

        binding.moviesButton.setOnClickListener {
            findNavController().navigate(R.id.action_staffMainMenuFragment_to_staffMovieFragment)
        }
        binding.moviesTextView.setOnClickListener {
            findNavController().navigate(R.id.action_staffMainMenuFragment_to_staffMovieFragment)
        }
    }

}