package com.example.movieticket.user.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.movieticket.R
import com.example.movieticket.databinding.FragmentCustomerSignUpBinding
import com.example.movieticket.user.data.UserViewModel

class CustomerSignUpFragment : Fragment() {
    private val viewModel: UserViewModel by activityViewModels()
    private lateinit var binding: FragmentCustomerSignUpBinding
//    private lateinit var userAuthDao: UserAuthDao
//    private lateinit var userProfileDao: UserProfileDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCustomerSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object;

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        userAuthDao = AppDatabase.getInstance(requireContext()).userDao()
//        userProfileDao = AppDatabase.getInstance(requireContext()).userProfileDao()

        binding.confirmSignUpButton.setOnClickListener {
            val username = binding.userInput.text.toString()
            val password = binding.passwordInput.text.toString()
            val confirmPassword = binding.passwordReInput.text.toString()

//            lifecycleScope.launch {
//                val queryResult = userAuthDao.searchByUsername(username)
//
//                if (queryResult.isNotEmpty()) {
//                    binding.errorPopup.visibility = View.VISIBLE
//                    binding.errorPopup.text = "Username '$username' has already been used. Please try another username!"
//                }
//                else if (password != confirmPassword) {
//                    binding.errorPopup.visibility = View.VISIBLE
//                    binding.errorPopup.text = "Password and confirm password does not match"
//                }
//                else {
//                    binding.errorPopup.visibility = View.INVISIBLE
//                    userAuthDao.insert(UserAuth(username, password))
//                    userProfileDao.insert(UserProfile(username, "unnamed", 0, false, "", ""))
//                    findNavController().navigate(R.id.action_customerSignUpFragment_to_customerLoginFragment)
//                }
//            }

            viewModel.checkUserExist(username) { signUpResult ->
                when (signUpResult) {
                    1 -> {
                        binding.errorPopup.visibility = View.VISIBLE
                        "Username '$username' has already been used. Please try another username!".also { binding.errorPopup.text = it }
                    }
                    2 -> {
                        if (password != confirmPassword) {
                            binding.errorPopup.visibility = View.VISIBLE
                            "Password and confirm password does not match".also { binding.errorPopup.text = it }
                        }
                        else {
                            binding.errorPopup.visibility = View.INVISIBLE
                            viewModel.addUserToRemoteDatabase(username, password)
                            findNavController().navigate(R.id.action_customerSignUpFragment_to_customerLoginFragment)
                    }
                }
            }
            }
        }
    }
}