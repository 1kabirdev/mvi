package com.mvi.ui.detailsUser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.mvi.data.service.api.ApiService
import com.mvi.data.service.api.impl.ApiHelperImpl
import com.mvi.data.factory.ViewModelDetailsFactory
import com.mvi.data.intent.DetailsIntent
import com.mvi.data.models.User
import com.mvi.databinding.ActivityDetailsUserBinding
import com.mvi.ui.detailsUser.viewmodel.DetailsViewModel
import com.mvi.ui.detailsUser.viewstate.DetailsState
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
        login = args?.get("LOGIN").toString()

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
                is DetailsState.Idle -> {

                }
                is DetailsState.Loading -> {

                }

                is DetailsState.Users -> {
                    setupUI(it.user)
                }

                is DetailsState.Error -> {

                }
            }
        }
    }


    private fun setupUI(user: User) = with(binding) {
        Glide.with(this@DetailsUserActivity).load(user.avatar_url).into(imageViewAvatar)
        textViewUserName.text = user.login
    }
}