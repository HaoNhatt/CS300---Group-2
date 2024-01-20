package com.example.movieticket.staff.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.movieticket.R
import com.example.movieticket.staff.data.Schedule
import com.example.movieticket.databinding.FragmentSchedulePageBinding
import com.example.movieticket.databinding.FragmentStaffTheaterSchedulesBinding
import com.example.movieticket.staff.data.StaffViewModel
import com.google.android.material.tabs.TabLayoutMediator
import java.util.Calendar
import java.util.Locale

class StaffTheaterSchedulesFragment : Fragment() {
    private lateinit var binding: FragmentStaffTheaterSchedulesBinding
    private val viewModel: StaffViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.todayDate = Calendar.getInstance()
        binding = FragmentStaffTheaterSchedulesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addShowtimeButton.setOnClickListener {
            findNavController().navigate(R.id.action_staffTheaterSchedulesFragment_to_addShowtimeFragment)
        }

        val schedulesPagerAdapter = SchedulesPagerAdapter(this)
        binding.schedulesPager.adapter = schedulesPagerAdapter

        TabLayoutMediator(binding.schedulesTablayout, binding.schedulesPager) { tab, position ->
            val date = viewModel.todayDate
            date.add(Calendar.DATE, 1)
            val day = date.get(Calendar.DAY_OF_MONTH)
            val month = date.getDisplayName(Calendar.MONTH, Calendar.SHORT_FORMAT, Locale.US)
            tab.text = "$day/$month"
        }.attach()


    }
}

class SchedulesPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 7

    override fun createFragment(position: Int): Fragment {
        // Return a NEW fragment instance in createFragment(int).
        val fragment = SchedulePageFragment()
        fragment.arguments = Bundle().apply {
            // The object is just an integer.
            putInt(ARG_OBJECT, position + 1)
        }
        return fragment
    }
}

private const val ARG_OBJECT = "object"

// Instances of this class are fragments representing a single
// object in the collection.
class SchedulePageFragment : Fragment() {
    private lateinit var binding: FragmentSchedulePageBinding
    private val viewModel: StaffViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSchedulePageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val removeList = mutableListOf<String>()
        val movieIDList = mutableListOf<String>()
        val theaterIDList = mutableListOf<String>()
        for (movie in viewModel.moviesList)
            movieIDList.add(movie.id)
        for (theater in viewModel.theatersList)
            theaterIDList.add(theater.id)
        for (schedule in viewModel.schedulesList)
            if (schedule.movieID !in movieIDList || schedule.theaterID !in theaterIDList)
                removeList.add(schedule.id)
        for (removeID in removeList)
            viewModel.deleteSchedule(removeID)

        val showtimesRecyclerView = binding.staffShowtimeRecyclerView
        val adapter = ScheduleAdapter(viewModel)
        showtimesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        showtimesRecyclerView.adapter = adapter

    }
}


class ScheduleAdapter(private val viewModel: StaffViewModel): RecyclerView.Adapter<ScheduleAdapter.ShowtimeHolder>() {
    class ShowtimeHolder(private val view:View) : RecyclerView.ViewHolder(view) {
        val movieTitleView = view.findViewById<TextView>(R.id.movieTitleTextview)!!
        val dateView = view.findViewById<TextView?>(R.id.dateTextview)!!
        val startTimeView = view.findViewById<TextView>(R.id.startTimeTextView)!!
        val endTimeView = view.findViewById<TextView>(R.id.endTimeTextView)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowtimeHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.staff_schedule_item, parent, false)

        return ShowtimeHolder(layout)
    }

    override fun getItemCount(): Int = viewModel.schedulesList.size

    override fun onBindViewHolder(holder: ShowtimeHolder, position: Int) {
        val showtimesList = viewModel.schedulesList
        val movieID = showtimesList[position].movieID
        val movie = viewModel.moviesList.first{ it.id == movieID }
        val movieTitle = movie.title
        val date = showtimesList[position].date
        val startTime = showtimesList[position].startTime
        val startTimeSplit = startTime.split('h')
        val h = startTimeSplit[0].toInt()
        val m = startTimeSplit[1].toInt() + movie.duration
        val endTime = ((h + m / 60) % 24).toString() + "h" + (m % 60).toString()

        holder.movieTitleView.text = movieTitle
        holder.dateView.text = date
        holder.startTimeView.text = startTime
        holder.endTimeView.text = endTime
//        holder.itemView.setOnClickListener {
//            viewModel.selectedMovieIndex = position
//            it.findNavController().navigate(R.id.action_staffMovieFragment_to_movieDetailFragment)
//        }
    }
}