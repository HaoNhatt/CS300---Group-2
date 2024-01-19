package com.example.movieticket.user.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.movieticket.R
import com.example.movieticket.database.AppDatabase
import com.example.movieticket.database.UserAuthDao
import com.example.movieticket.database.UserProfileDao
import com.example.movieticket.databinding.FragmentCustomerLoginBinding
import com.example.movieticket.user.data.UserViewModel

class CustomerLoginFragment : Fragment() {

    companion object;

    private val viewModel: UserViewModel by activityViewModels()
    private lateinit var binding: FragmentCustomerLoginBinding
    private lateinit var userAuthDao: UserAuthDao
    private lateinit var userProfileDao: UserProfileDao
//    private var accountList = mutableListOf< Pair< String, String > >()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCustomerLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        userAuthDao = AppDatabase.getInstance(requireContext()).userDao()
        userProfileDao = AppDatabase.getInstance(requireContext()).userProfileDao()

        binding.loginButton.setOnClickListener {
            val username = binding.userInput.text.toString()
            val password = binding.passwordInput.text.toString()

//            lifecycleScope.launch {
//                val queryResult = userAuthDao.searchByUsername(username)
//
//                if (queryResult.isEmpty()) {
//                    binding.errorPopup.visibility = View.VISIBLE
//                    binding.errorPopup.text = "Incorrect username. Please try again!"
//                }
//                else if (password != queryResult.first().password) {
//                    binding.errorPopup.visibility = View.VISIBLE
//                    binding.errorPopup.text = "Incorrect password. Please try again!"
//                }
//                else {
//                    binding.errorPopup.visibility = View.INVISIBLE
//                    val userProfile = userProfileDao.searchByUsername(username)
//                    viewModel.logIn(userProfile.first())
//                    findNavController().navigate(R.id.action_customerLoginFragment_to_customerMainMenuFragment)
//                }
//            }

            viewModel.tryLogin(username, password) { loginResult ->
                when (loginResult) {
                    1 -> {
                        binding.errorPopup.visibility = View.VISIBLE
                        "Incorrect username. Please try again!".also { binding.errorPopup.text = it }
                    }

                    2 -> {
                        binding.errorPopup.visibility = View.VISIBLE
                        "Incorrect password. Please try again!".also { binding.errorPopup.text = it }
                    }

                    3 -> {
                        binding.errorPopup.visibility = View.INVISIBLE
                        viewModel.logIn(username)
                        findNavController().navigate(R.id.action_customerLoginFragment_to_customerMainMenuFragment)
                    }
                }
            }

        }

        binding.signUpButton.setOnClickListener {
            findNavController().navigate(R.id.action_customerLoginFragment_to_customerSignUpFragment)
        }

        binding.staffButton.setOnClickListener {
            findNavController().navigate(R.id.action_customerLoginFragment_to_staffLoginFragment)
        }

    }

}