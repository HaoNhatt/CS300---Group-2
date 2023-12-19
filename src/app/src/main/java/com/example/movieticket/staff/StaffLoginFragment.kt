package com.example.movieticket.staff

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.movieticket.R
import com.example.movieticket.staff.data.StaffViewModel
import com.example.movieticket.databinding.FragmentStaffLoginBinding

class StaffLoginFragment : Fragment() {

    private lateinit var viewModel: StaffViewModel
    private lateinit var binding: FragmentStaffLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(this)[StaffViewModel::class.java]
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