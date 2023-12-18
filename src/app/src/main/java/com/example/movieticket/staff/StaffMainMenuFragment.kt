package com.example.movieticket

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movieticket.databinding.FragmentStaffMainMenuBinding
import com.example.movieticket.staff.data.StaffViewModel

class StaffMainMenuFragment : Fragment() {

    companion object {
        fun newInstance() = StaffMainMenuFragment()
    }

    private lateinit var viewModel: StaffViewModel
    private lateinit var binding: FragmentStaffMainMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(this)[StaffViewModel::class.java]
        binding = FragmentStaffMainMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


    }

}