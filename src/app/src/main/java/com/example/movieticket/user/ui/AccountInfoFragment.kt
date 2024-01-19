package com.example.movieticket.user.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.movieticket.R
import com.example.movieticket.database.AppDatabase
import com.example.movieticket.database.UserProfileDao
import com.example.movieticket.databinding.FragmentAccountInfoBinding
import com.example.movieticket.user.data.UserViewModel
import kotlinx.coroutines.launch

class AccountInfoFragment : Fragment() {
    private val viewModel: UserViewModel by activityViewModels()
    private lateinit var userProfileDao: UserProfileDao
    private lateinit var binding: FragmentAccountInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAccountInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userProfileDao = AppDatabase.getInstance(requireContext()).userProfileDao()

        binding.accountUsername.setText(viewModel.getUser().name)
        binding.accountAge.setText(viewModel.getUser().age.toString())
        val male = viewModel.getUser().sex
        if (male) {
            binding.accountMaleCheck.isChecked = true
        } else {
            binding.accountFemaleCheck.isChecked = true
        }
        binding.accountEmail.setText(viewModel.getUser().email)
        binding.accountPhone.setText(viewModel.getUser().phone)
//        binding.accountWallet.setText(viewModel.getUser().wallet.toString())


        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_customerAccountInfoFragment_to_customerMainMenuFragment)
        }

        binding.saveButton.setOnClickListener {
            val newName = binding.accountUsername.text.toString()
            val newAge = binding.accountAge.text.toString().toInt()
            val newSex = binding.accountMaleCheck.isChecked
            val newEmail = binding.accountEmail.text.toString()
            val newPhone = binding.accountPhone.text.toString()
//            val newWallet = binding.accountWallet.text.toString().toInt()

            viewModel.setUser(newName, newAge, newSex, newEmail, newPhone)
            viewModel.updateUserInformation()

//            lifecycleScope.launch {
//                userProfileDao.updateUser(
//                    viewModel.getUser().username,
//                    viewModel.getUser().name,
//                    viewModel.getUser().age,
//                    viewModel.getUser().sex,
//                    viewModel.getUser().email,
//                    viewModel.getUser().phone,
////                    viewModel.getUser().wallet
//                )
//            }
            findNavController().navigate(R.id.action_customerAccountInfoFragment_to_customerMainMenuFragment)
        }
    }

    companion object
}