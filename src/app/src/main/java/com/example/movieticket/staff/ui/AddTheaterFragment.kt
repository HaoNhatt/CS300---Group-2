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
import com.example.movieticket.databinding.FragmentAddTheaterBinding
import com.example.movieticket.databinding.FragmentStaffTheatersBinding
import com.example.movieticket.staff.data.StaffViewModel

class AddTheaterFragment : Fragment() {
    private val viewModel: StaffViewModel by activityViewModels()
    private lateinit var binding: FragmentAddTheaterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddTheaterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.addTheaterButton2.setOnClickListener {
            val name = binding.theaterNameInput.text.toString()
            val address = binding.theaterAddressInput.text.toString()
            viewModel.addTheater(name, address) {
                findNavController().navigate(R.id.action_addTheaterFragment2_to_staffTheatersFragment)
            }
        }
    }

}