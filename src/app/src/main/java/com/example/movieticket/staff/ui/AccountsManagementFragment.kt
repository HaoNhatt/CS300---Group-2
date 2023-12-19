package com.example.movieticket.staff.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.movieticket.databinding.FragmentAccountsManagementBinding
import com.example.movieticket.staff.data.StaffViewModel


class AccountsManagementFragment : Fragment() {
    private lateinit var viewModel: StaffViewModel
    private lateinit var binding: FragmentAccountsManagementBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(this)[StaffViewModel::class.java]
        binding = FragmentAccountsManagementBinding.inflate(inflater, container, false)
        return binding.root
    }


}