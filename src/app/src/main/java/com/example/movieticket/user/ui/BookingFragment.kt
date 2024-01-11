package com.example.movieticket.user.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.movieticket.R
import com.example.movieticket.databinding.FragmentBookingBinding
import com.example.movieticket.user.data.UserViewModel

class BookingFragment : Fragment() {
    private lateinit var viewModel: UserViewModel
    private lateinit var binding: FragmentBookingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(this)[UserViewModel::class.java]
        binding = FragmentBookingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backArrow.setOnClickListener {
            findNavController().navigate(R.id.action_customerBookingFragment_to_customerMovieInfoFragment)
        }

        binding.chooseSeatButton.setOnClickListener {
            findNavController().navigate(R.id.action_customerBookingFragment_to_customerChooseSeatFragment)
        }
    }

    companion object
}