package com.example.movieticket.staff.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.movieticket.R
import com.example.movieticket.databinding.FragmentAddShowtimeBinding
import com.example.movieticket.staff.data.StaffViewModel
import java.util.Calendar
import java.util.Locale

class AddShowtimeFragment : Fragment() {
    private val viewModel: StaffViewModel by activityViewModels()
    private lateinit var binding: FragmentAddShowtimeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAddShowtimeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieTitlesList = getAvailableMovieTitlesList()
        val datesList = getAvailableDatesList()
        binding.movieSpinner.adapter = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            movieTitlesList
        )
        binding.dateSpinner.adapter = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            datesList
        )

        binding.timePicker.setIs24HourView(true)

        binding.addShowtimeButton2.setOnClickListener {
            val movieTitle = binding.movieSpinner.selectedItem.toString()
            val movieID = viewModel.moviesList.first { it.title == movieTitle }.id
            val theaterID = viewModel.theatersList[viewModel.selectedTheaterIndex].id
            val date = binding.dateSpinner.selectedItem.toString()
            val timePicker = binding.timePicker
            val time = "${timePicker.hour}h${timePicker.minute}"

            viewModel.addSchedule(movieID, theaterID, date, time) {
            findNavController().navigate(R.id.action_addShowtimeFragment_to_staffTheaterSchedulesFragment)
            }
        }

    }

    private fun getAvailableMovieTitlesList(): MutableList<String> {
        val movieTitlesList = mutableListOf<String>()
        for (movie in viewModel.moviesList)
            movieTitlesList.add(movie.title)
        return movieTitlesList
    }

    private fun getAvailableDatesList(): MutableList<String> {
        val datesList = mutableListOf<String>()
        viewModel.todayDate = Calendar.getInstance()
        val date = viewModel.todayDate

        for (i in 0 until 7) {
            date.add(Calendar.DATE, 1)
            val day = date.get(Calendar.DAY_OF_MONTH)
            val month = date.getDisplayName(Calendar.MONTH, Calendar.SHORT_FORMAT, Locale.US)
            val year = date.get(Calendar.YEAR)
            datesList.add("$day/$month/$year")
        }
        return datesList
    }

}