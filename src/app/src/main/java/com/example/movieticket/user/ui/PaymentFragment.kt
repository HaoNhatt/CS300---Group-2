package com.example.movieticket.user.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.movieticket.R
import com.example.movieticket.databinding.FragmentCustomerMainMenuBinding
import com.example.movieticket.databinding.FragmentPaymentBinding
import com.example.movieticket.user.data.UserViewModel

class PaymentFragment : Fragment() {
    private lateinit var viewModel: UserViewModel
    private lateinit var binding: FragmentPaymentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(this)[UserViewModel::class.java]
        binding = FragmentPaymentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {

    }
}