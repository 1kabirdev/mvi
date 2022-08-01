package com.mvi.ui.detailsUser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.mvi.data.service.api.ApiService
import com.mvi.data.service.api.impl.ApiHelperImpl
import com.mvi.factory.ViewModelDetailsFactory
import com.mvi.intent.DetailsIntent
import com.mvi.data.models.User
import com.mvi.databinding.ActivityDetailsUserBinding
import com.mvi.ui.detailsUser.viewmodel.DetailsViewModel
import com.mvi.ui.detailsUser.viewstate.DetailsState
import com.mvi.utils.Constants
import kotlinx.coroutines.launch

class DetailsUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsUserBinding
    private lateinit var detailsViewModel: DetailsViewModel

    private var login: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val args = intent.extras
        login = args?.get(Constants.LOGIN).toString()

        setupViewModel()
        observeViewModel()
    }

    private fun setupViewModel() {
        detailsViewModel = ViewModelProviders.of(
            this,
            ViewModelDetailsFactory(
                ApiHelperImpl(
                    ApiService.RetrofitBuilder.apiService
                )
            )
        )[DetailsViewModel::class.java]
    }

    private fun observeViewModel() = lifecycleScope.launch {
        detailsViewModel.userIntent.send(DetailsIntent.User)
        detailsViewModel.handlerIntentUser(login)
        detailsViewModel.state.collect {
            when (it) {
                is DetailsState.Idle -> {}

                is DetailsState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is DetailsState.Users -> {
                    binding.progressBar.visibility = View.GONE
                    setupUI(it.user)
                }

                is DetailsState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this@DetailsUserActivity, it.error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setupUI(user: User) = with(binding) {
        Glide.with(this@DetailsUserActivity).load(user.avatar_url).into(imageViewAvatar)
        textViewUserName.text = user.login
    }
}