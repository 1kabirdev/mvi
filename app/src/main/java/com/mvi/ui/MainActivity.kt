package com.mvi.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mvi.R
import com.mvi.data.User
import com.mvi.data.api.impl.ApiHelperImpl
import com.mvi.data.api.RetrofitBuilder
import com.mvi.databinding.ActivityMainBinding
import com.mvi.ui.intent.MainIntent
import com.mvi.ui.viewmodel.MainViewModel
import com.mvi.ui.viewstate.MainState
import com.mvi.utils.ViewModelFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private var adapter = MainAdapter(arrayListOf())
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
                    RetrofitBuilder.apiService
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
}