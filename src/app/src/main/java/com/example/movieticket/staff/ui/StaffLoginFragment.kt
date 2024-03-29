package com.example.movieticket.staff.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.movieticket.R
import com.example.movieticket.staff.data.StaffViewModel
import com.example.movieticket.databinding.FragmentStaffLoginBinding

class StaffLoginFragment : Fragment() {

    private val viewModel: StaffViewModel by activityViewModels()
    private lateinit var binding: FragmentStaffLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentStaffLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {
            if (viewModel.tryLogin(binding.username.toString(), binding.password.toString())) {
                findNavController().navigate(R.id.action_staffLoginFragment_to_staffMainMenuFragment)
            }
        }
    }

}