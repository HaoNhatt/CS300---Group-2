package com.example.movieticket.staff.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieticket.databinding.FragmentStaffTheatersBinding
import com.example.movieticket.staff.data.StaffViewModel


class StaffTheatersFragment : Fragment() {

    private lateinit var viewModel: StaffViewModel
    private lateinit var binding: FragmentStaffTheatersBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(this)[StaffViewModel::class.java]
        binding = FragmentStaffTheatersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addTheaterButton.visibility = when (viewModel.staff.isManager) {
            true -> {View.VISIBLE}
            else -> {View.INVISIBLE}
        }

        val theatersRecyclerView = binding.theatersRecyclerView
        val adapter = StaffTheatersAdapter(viewModel)
        theatersRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        theatersRecyclerView.adapter = adapter
    }

}