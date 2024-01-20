package com.example.movieticket.user.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieticket.R
import com.example.movieticket.databinding.FragmentCustomerBookedHistoryBinding
import com.example.movieticket.user.data.UserViewModel

class CustomerBookedHistoryFragment : Fragment() {
    private val viewModel: UserViewModel by activityViewModels()
    private lateinit var binding: FragmentCustomerBookedHistoryBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCustomerBookedHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_customerBookedHistoryFragment_to_customerMainMenuFragment)
        }

        val ticketsRecyclerView = binding.bookedTicketHistory
        val adapter = CustomerBookedHistoryAdapter(viewModel)
        ticketsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        ticketsRecyclerView.adapter = adapter
    }

    companion object
}