package com.example.movieticket.user.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieticket.R
import com.example.movieticket.databinding.FragmentChooseSeatBinding
import com.example.movieticket.user.data.UserViewModel

class ChooseSeatFragment : Fragment() {
    private val viewModel: UserViewModel by activityViewModels()
    private lateinit var binding: FragmentChooseSeatBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentChooseSeatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backArrow.setOnClickListener {
            findNavController().navigate(R.id.action_customerChooseSeatFragment_to_customerChooseScheduleFragment)
        }

        binding.continueButton.setOnClickListener {
            findNavController().navigate(R.id.action_customerChooseSeatFragment_to_customerFinishBookingFragment)
        }

        val seatsRecyclerView = binding.seatGrid
        val adapter = CustomerSeatAdapter(viewModel)
        seatsRecyclerView.layoutManager = GridLayoutManager(requireContext(), 10)
        seatsRecyclerView.adapter = adapter
    }

    companion object
}