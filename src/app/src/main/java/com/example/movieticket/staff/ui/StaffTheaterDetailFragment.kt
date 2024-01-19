package com.example.movieticket.staff.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.movieticket.R
import com.example.movieticket.databinding.FragmentTheaterDetailBinding
import com.example.movieticket.staff.data.StaffViewModel


class StaffTheaterDetailFragment : Fragment() {
    private val viewModel: StaffViewModel by activityViewModels()
    private lateinit var binding: FragmentTheaterDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTheaterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val selectedTheater = viewModel.theatersList[viewModel.selectedTheaterIndex]
        binding.theaterNameDetail.setText(selectedTheater.name)
        binding.theaterAddressDetail.setText(selectedTheater.address)

        binding.saveChangesTheaterDetailButton.setOnClickListener {
            val name = binding.theaterNameDetail.text.toString()
            val address = binding.theaterAddressDetail.text.toString()
            viewModel.modifyTheater(viewModel.selectedTheaterIndex, name, address)
        }

        binding.deleteTheaterButton.setOnClickListener {
            viewModel.deleteTheater(viewModel.selectedTheaterIndex)
            findNavController().navigate(R.id.action_theaterDetailFragment_to_staffTheatersFragment)
        }

        binding.goToSetSchedulesButton.setOnClickListener {
            findNavController().navigate((R.id.action_theaterDetailFragment_to_staffTheaterSchedulesFragment))
        }
    }
}