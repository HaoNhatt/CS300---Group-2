package com.example.movieticket.user.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieticket.R
import com.example.movieticket.databinding.FragmentChooseSeatBinding
import com.example.movieticket.user.data.SeatType
import com.example.movieticket.user.data.UserViewModel

class ChooseSeatFragment : Fragment(), CustomerSeatAdapter.OnSeatSelectedListener {
    private val viewModel: UserViewModel by activityViewModels()
    private lateinit var binding: FragmentChooseSeatBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentChooseSeatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.movieTitleInChooseSeat.text = viewModel.moviesList[viewModel.selectedMovieIndex].title
        binding.theaterNameInChooseSeat.text = viewModel.theatersList[viewModel.selectedTheaterIndex].name
        "${viewModel.filteredSchedulesList[viewModel.selectedScheduleIndex].startTime} : ${viewModel.filteredSchedulesList[viewModel.selectedScheduleIndex].date}".also { binding.scheduleDateInChooseSeat.text = it }

        viewModel.filterSeat()

        binding.backArrow.setOnClickListener {
            findNavController().navigate(R.id.action_customerChooseSeatFragment_to_customerChooseScheduleFragment)
        }

        binding.continueButton.setOnClickListener {
            viewModel.exportTicket()
            findNavController().navigate(R.id.action_customerChooseSeatFragment_to_customerFinishBookingFragment)
        }

        val seatsRecyclerView = binding.seatGrid
        val adapter = CustomerSeatAdapter(viewModel, this)
        seatsRecyclerView.layoutManager = GridLayoutManager(requireContext(), 9)
        seatsRecyclerView.adapter = adapter
    }

    override fun onSeatSelected() {
        viewModel.totalSeatCost = if (viewModel.seatType == SeatType.NORMAL) {
            viewModel.seatSelectingList.size * viewModel.NORMALSEATTYPE
        } else {
            viewModel.seatSelectingList.size * viewModel.VIPSEATTYPE
        }
        "${viewModel.totalSeatCost}.000".also { binding.totalCost.text = it }
    }

    companion object
}