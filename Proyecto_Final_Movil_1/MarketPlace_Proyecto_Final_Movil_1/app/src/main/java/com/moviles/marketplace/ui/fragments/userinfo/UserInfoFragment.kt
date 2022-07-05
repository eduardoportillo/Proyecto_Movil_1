package com.moviles.marketplace.ui.fragments.userinfo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.moviles.marketplace.BottomNavigationActivity
import com.moviles.marketplace.MarketPlaceApplication.Companion.sharedPref
import com.moviles.marketplace.api.UserRepository
import com.moviles.marketplace.databinding.FragmentUserInfoBinding
import com.moviles.marketplace.models.User
import com.moviles.marketplace.ui.activities.auth.LoginActivity

class UserInfoFragment : Fragment(), UserRepository.GetUserListener {
    private var _binding: FragmentUserInfoBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentUserInfoBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onResume() {
        super.onResume()
        fetchSetup()
        buttonSetup()
    }

    private fun fetchSetup() {
        fetchProductByUserList()
    }

    fun buttonSetup(){
        binding.btnSalir.setOnClickListener {
            sharedPref.setToken("")
            val loginIntent = Intent(getActivity(), LoginActivity::class.java)
            getActivity()?.startActivity(loginIntent)
        }
    }
    private fun fetchProductByUserList() {
        UserRepository().getUser(this)
    }
    override fun getUserReady(user: User) {
        binding.idLabel.text = user.id.toString()
        binding.emailLabel.text = user.email
        binding.nameLabel.text = user.name
        binding.notificatioIdLabel.text = user.notification_id
    }

    override fun onUserGetError(t: Throwable) {
        Log.d("error_response_api", t.toString())
    }
}