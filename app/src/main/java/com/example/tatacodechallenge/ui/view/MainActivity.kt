package com.example.tatacodechallenge.ui.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isEmpty
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tatacodechallenge.R
import com.example.tatacodechallenge.databinding.ActivityMainBinding
import com.example.tatacodechallenge.helper.adapter.RandomStringAdapter
import com.example.tatacodechallenge.ui.viewmodel.RandomStringViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: RandomStringViewModel by viewModels()
    private lateinit var adapter: RandomStringAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = RandomStringAdapter(emptyList()) {
            viewModel.deleteItem(it)
        }

        binding.rvList.layoutManager = LinearLayoutManager(this)
        binding.rvList.adapter = adapter

        binding.btnGenerate.setOnClickListener {
            val lengthStr = binding.etLength.text.toString()
            if (lengthStr.isNotEmpty()) {
                val length = lengthStr.toIntOrNull()
                if (length != null && length > 0) {
                    viewModel.fetchRandomString(length)
                } else {
                    Toast.makeText(this, getString(R.string.str_validation), Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.btnDeleteAll.setOnClickListener {
            viewModel.deleteAll()
        }

        viewModel.randomStrings.observe(this) {
            adapter = RandomStringAdapter(it) { index ->
                viewModel.deleteItem(index)
            }
            binding.rvList.adapter = adapter
            binding.btnDeleteAll.isEnabled = binding.rvList.isEmpty() != true
        }

        viewModel.error.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }
}