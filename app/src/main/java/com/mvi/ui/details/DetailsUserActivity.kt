package com.mvi.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mvi.databinding.ActivityDetailsUserBinding

class DetailsUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}