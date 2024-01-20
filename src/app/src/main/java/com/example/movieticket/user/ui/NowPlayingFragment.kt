package com.example.movieticket.user.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieticket.R
import com.example.movieticket.databinding.FragmentNowPlayingBinding
import com.example.movieticket.user.data.UserViewModel

class NowPlayingFragment : Fragment() {
    private val viewModel: UserViewModel by activityViewModels()
    private lateinit var binding: FragmentNowPlayingBinding
//    private lateinit var movieDao: MovieDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentNowPlayingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        movieDao = AppDatabase.getInstance(requireContext()).movieDao()

        viewModel.filterMovies("", false)

        binding.customerAccountIcon.setOnClickListener {
            findNavController().navigate(R.id.action_nowPlayingFragment_to_customerAccountInfoFragment)
        }

        binding.customerBookedHistoryIcon.setOnClickListener {
            findNavController().navigate(R.id.action_nowPlayingFragment_to_customerBookedHistoryFragment)
        }

        binding.groupUpcoming.setOnClickListener {
            findNavController().navigate(R.id.action_nowPlayingFragment_to_customerMainMenuFragment)
        }

        val moviesRecyclerView = binding.listMovies
        val adapter = CustomerMovieAdapter(viewModel)
        moviesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        moviesRecyclerView.adapter = adapter

        binding.searchIcon.setOnClickListener {
            binding.searchBar.clearFocus()
            val searchKeyWord = binding.searchBar.text.toString()
            if (searchKeyWord.isEmpty()) {
                viewModel.filterMovies(searchKeyWord, false)
            }
            viewModel.filterMovies(searchKeyWord, true)
            moviesRecyclerView.run { adapter.run { notifyDataSetChanged() } }
        }
    }

    companion object
}