package com.mvi.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mvi.data.api.ApiService
import com.mvi.data.models.User
import com.mvi.data.api.impl.ApiHelperImpl
import com.mvi.databinding.ActivityMainBinding
import com.mvi.ui.main.adapter.MainAdapter
import com.mvi.ui.main.intent.MainIntent
import com.mvi.ui.main.viewmodel.MainViewModel
import com.mvi.ui.main.viewstate.MainState
import com.mvi.data.factory.ViewModelFactory
import com.mvi.ui.details.DetailsUserActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity(), MainAdapter.OnClickListener {

    private lateinit var mainViewModel: MainViewModel
    private var adapter = MainAdapter(arrayListOf(), this)
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        setupViewModel()
        observeViewModel()
    }

    private fun setupUI() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.run {
            addItemDecoration(
                DividerItemDecoration(
                    binding.recyclerView.context,
                    (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
                )
            )
        }
        binding.recyclerView.adapter = adapter
    }


    private fun setupViewModel() {
        mainViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                ApiHelperImpl(
                    ApiService.RetrofitBuilder.apiService
                )
            )
        )[MainViewModel::class.java]
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            mainViewModel.userIntent.send(MainIntent.FetchUser)
            mainViewModel.state.collect {
                when (it) {
                    is MainState.Idle -> {

                    }
                    is MainState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    is MainState.Users -> {
                        binding.progressBar.visibility = View.GONE
                        renderList(it.user)
                    }
                    is MainState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this@MainActivity, it.error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun renderList(users: List<User>) {
        binding.recyclerView.visibility = View.VISIBLE
        users.let { data ->
            adapter.addData(data)
        }
        adapter.notifyDataSetChanged()
    }

    override fun onClickUser(login: String) {
        startActivity(
            Intent(
                this,
                DetailsUserActivity::class.java
            ).putExtra("LOGIN", login)
        )
    }
}