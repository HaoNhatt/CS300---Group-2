package com.example.movieticket.user.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.movieticket.databinding.FragmentChooseSeatBinding
import com.example.movieticket.user.data.UserViewModel

class ChooseSeatFragment : Fragment() {
    private lateinit var viewModel: UserViewModel
    private lateinit var binding: FragmentChooseSeatBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(this)[UserViewModel::class.java]
        binding = FragmentChooseSeatBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object
}