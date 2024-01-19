package com.example.movieticket.staff.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieticket.R
import com.example.movieticket.databinding.FragmentStaffTheatersBinding
import com.example.movieticket.staff.data.StaffViewModel


class StaffTheatersFragment : Fragment() {
    private val viewModel: StaffViewModel by activityViewModels()
    private lateinit var binding: FragmentStaffTheatersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentStaffTheatersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addTheaterButton.setOnClickListener {
            findNavController().navigate(R.id.action_staffTheatersFragment_to_addTheaterFragment2)
        }

        binding.addTheaterButton.visibility = when (viewModel.staff.isManager) {
            true -> {View.VISIBLE}
            else -> {View.INVISIBLE}
        }

        val theatersRecyclerView = binding.theatersRecyclerView
        val adapter = StaffTheatersAdapter(viewModel)
        theatersRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        theatersRecyclerView.adapter = adapter

        Log.d("==========", viewModel.theatersList.size.toString())
    }
}


class StaffTheatersAdapter(private val viewModel: StaffViewModel): RecyclerView.Adapter<StaffTheatersAdapter.TheaterViewHolder>() {
    class TheaterViewHolder(private val view:View) : RecyclerView.ViewHolder(view) {
        val theaterNameView = view.findViewById<TextView>(R.id.textViewItem)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TheaterViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.textview_item, parent, false)

        return TheaterViewHolder(layout)
    }

    override fun getItemCount(): Int = viewModel.theatersList.size

    override fun onBindViewHolder(holder: TheaterViewHolder, position: Int) {
        holder.theaterNameView.text = viewModel.theatersList[position].name
        holder.itemView.setOnClickListener {
            viewModel.selectedTheaterIndex = position
            it.findNavController().navigate(R.id.action_staffTheatersFragment_to_theaterDetailFragment)
        }
    }
}