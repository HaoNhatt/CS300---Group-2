package com.example.movieticket.Customer

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.movieticket.R
import com.example.movieticket.database.UserAuthDao
import com.example.movieticket.databinding.FragmentCustomerLoginBinding
import com.example.movieticket.user.UserViewModel

class CustomerLoginFragment : Fragment() {

    companion object {
        fun newInstance() = CustomerLoginFragment()
    }

    private lateinit var viewModel: UserViewModel
    private lateinit var binding: FragmentCustomerLoginBinding
    private lateinit var userAuthDao: UserAuthDao
    private var accountList = mutableListOf< Pair< String, String > >()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(this)[UserViewModel::class.java]
        binding = FragmentCustomerLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        lifecycleScope.launch {
//            val queryResult = userAuthDao.getAll()
//            for (account in queryResult) {
//                accountList.add(Pair(account.username, account.password))
//            }
//        }

        binding.loginButton.setOnClickListener {
            val username = binding.userInput.text.toString()
            val password = binding.passwordInput.text.toString()
            var correct = false

            for (account in accountList)
                correct = correct || (username == account.first && password == account.second)


            if (correct) {
                binding.errorPopup.visibility = View.INVISIBLE
//                startActivity(Intent(requireContext(), MainActivity::class.java))
            }
            else
                binding.errorPopup.visibility = View.VISIBLE
                binding.errorPopup.text = "Incorrect username or password, please try again!"
        }

        binding.signUpButton.setOnClickListener{
            findNavController().navigate(R.id.action_customer_login_to_signupFragment)
        }

    }

}