package com.example.movieticket.user.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.movieticket.R
import com.example.movieticket.databinding.FragmentChooseSeatBinding
import com.example.movieticket.user.data.UserViewModel

class ChooseSeatFragment : Fragment() {
    private lateinit var viewModel: UserViewModel
    private lateinit var binding: FragmentChooseSeatBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(this)[UserViewModel::class.java]
        binding = FragmentChooseSeatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backArrow.setOnClickListener {
            findNavController().navigate(R.id.action_customerChooseSeatFragment_to_customerBookingFragment)
        }

        binding.continueButton.setOnClickListener {
            findNavController().navigate(R.id.action_customerChooseSeatFragment_to_customerPaymentFragment)
        }
    }

    companion object
}