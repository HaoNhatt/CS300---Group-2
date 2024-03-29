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
import com.example.movieticket.databinding.FragmentChooseTheaterBinding
import com.example.movieticket.user.data.UserViewModel

class ChooseTheaterFragment : Fragment() {
    private val viewModel: UserViewModel by activityViewModels()
    private lateinit var binding: FragmentChooseTheaterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChooseTheaterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backArrow.setOnClickListener {
            findNavController().navigate(R.id.action_customerChooseTheaterFragment_to_customerMovieInfoFragment)
        }

        val theatersRecyclerView = binding.listTheaters
        val adapter = CustomerTheaterAdapter(viewModel)
        theatersRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        theatersRecyclerView.adapter = adapter
    }

    companion object
}