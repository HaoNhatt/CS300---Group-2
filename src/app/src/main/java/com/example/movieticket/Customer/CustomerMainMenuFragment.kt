package com.example.movieticket

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movieticket.Customer.CustomerViewModel
import com.example.movieticket.databinding.FragmentCustomerMainMenuBinding

class CustomerMainMenuFragment : Fragment() {

    companion object {
        fun newInstance() = CustomerMainMenuFragment()
    }

    private lateinit var viewModel: CustomerViewModel
    private lateinit var binding: FragmentCustomerMainMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(this)[CustomerViewModel::class.java]
        binding = FragmentCustomerMainMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


    }

}