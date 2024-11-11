package com.example.pussies.presentation

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.pussies.R
import com.example.pussies.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initAndObserveViewModel()
        loadPussyData()

        binding.buttonLoadPussy.setOnClickListener {
            loadPussyData()
        }

    }

    private fun initAndObserveViewModel() {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        observeLoading()
        observeErrors()
        observePussyModel()
    }

    private fun observeLoading() {
        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) VISIBLE else GONE
        }
    }

    private fun observeErrors() {
        viewModel.isError.observe(this) { isError ->
            if (isError) {
                with(binding) {
                    pussyImage.setImageResource(R.drawable.error_pussy)
                    pussyBreed.text = getString(R.string.sadness)
                }
                showAlertDialog()
            }
        }
    }

    private fun showAlertDialog() {
        AlertDialog.Builder(this)
            .setTitle("Error")
            .setMessage(R.string.error_message)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    private fun observePussyModel() {
        viewModel.pussy.observe(this) { pussy ->
            with(binding) {
                pussyImage.load(pussy.url)
                pussyBreed.text = pussy.breeds[0].name
            }
        }
    }

    private fun loadPussyData() {
        lifecycleScope.launch {
            viewModel.loadPussyData()
        }
    }
}