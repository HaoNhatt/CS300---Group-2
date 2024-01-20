package com.example.movieticket.user.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.movieticket.R
import com.example.movieticket.databinding.FragmentFinishBookingBinding

class FinishBookingFragment : Fragment() {
    private lateinit var binding: FragmentFinishBookingBinding
//    private lateinit var invoiceDao: InvoiceDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFinishBookingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.confirmButton.setOnClickListener {
            findNavController().navigate(R.id.action_customerFinishBookingFragment_to_customerMainMenuFragment)
        }
    }

    companion object
}