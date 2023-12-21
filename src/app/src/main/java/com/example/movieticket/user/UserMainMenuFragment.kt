package com.example.movieticket

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movieticket.database.MovieDao
import com.example.movieticket.database.ScheduleDao
import com.example.movieticket.user.UserViewModel
import com.example.movieticket.databinding.FragmentCustomerMainMenuBinding

class UserMainMenuFragment : Fragment() {

    companion object {
        fun newInstance() = UserMainMenuFragment()
    }

    private lateinit var viewModel: UserViewModel
    private lateinit var binding: FragmentCustomerMainMenuBinding
    private lateinit var movieDao: MovieDao
    private lateinit var theaterDao: MovieDao
    private lateinit var scheduleDao: ScheduleDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(this)[UserViewModel::class.java]
        binding = FragmentCustomerMainMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


    }

}