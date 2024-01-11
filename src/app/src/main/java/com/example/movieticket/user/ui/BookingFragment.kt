package com.example.movieticket.user.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieticket.R
import com.example.movieticket.databinding.FragmentBookingBinding
import com.example.movieticket.user.data.UserViewModel
import kotlinx.coroutines.launch

class BookingFragment : Fragment() {
    private val viewModel: UserViewModel by activityViewModels()
    private lateinit var binding: FragmentBookingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentBookingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            val scheduleItem = layoutInflater.inflate(R.layout.list_schedule_item, null)
            val parentLayout = binding.schedulesList

            parentLayout.addView(scheduleItem)
        }

//        Log.d("HaoNhat", "Test 1")
//        viewModel.filterSchedule(viewModel.moviesList[viewModel.selectedMovieIndex].uid)
//        Log.d("HaoNhat", "Test 2")
//
//        binding.backArrow.setOnClickListener {
//            findNavController().navigate(R.id.action_customerBookingFragment_to_customerMovieInfoFragment)
//        }
//
//        Log.d("HaoNhat", "Test 3")
//        val schedulesRecyclerView = binding.schedulesList
//        val adapter = CustomerScheduleAdapter(viewModel)
//        schedulesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
//        schedulesRecyclerView.adapter = adapter
//        Log.d("HaoNhat", "Test 4")
    }

    companion object
}