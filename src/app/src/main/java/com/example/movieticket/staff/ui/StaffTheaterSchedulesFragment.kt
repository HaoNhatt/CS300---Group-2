package com.example.movieticket.staff.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.movieticket.R
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
        viewModel.selectedDate = viewModel.todayDate

        binding.addShowtimeButton.setOnClickListener {
            findNavController().navigate(R.id.action_staffTheaterSchedulesFragment_to_addShowtimeFragment)
        }

        val schedulesPagerAdapter = SchedulesPagerAdapter(this)
        binding.schedulesPager.adapter = schedulesPagerAdapter

        TabLayoutMediator(binding.schedulesTablayout, binding.schedulesPager) { tab, position ->
            val date = viewModel.todayDate
            date.add(Calendar.DATE, position + 1)
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
//        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
////            val textView: TextView = view.findViewById(android.R.id.text1)
////            binding. = getInt(ARG_OBJECT).toString()
//        }


    }
}