package com.example.pussies

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import coil.load
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    private lateinit var pussyImage: ImageView
    private lateinit var progressBar: ProgressBar
    private lateinit var pussyBreed: TextView
    private lateinit var buttonLoadPussy: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initViews()
        initAndObserveViewModel()
        loadPussyData()

        buttonLoadPussy.setOnClickListener {
            loadPussyData()
        }

    }

    private fun initViews() {
        pussyImage = findViewById(R.id.pussyImage)
        progressBar = findViewById(R.id.progressBar)
        pussyBreed = findViewById(R.id.pussyBreed)
        buttonLoadPussy = findViewById(R.id.buttonLoadPussy)
    }

    private fun initAndObserveViewModel() {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        observeLoading()
        observeErrors()
        observePussyModel()
    }

    private fun observeLoading() {
        viewModel.isLoading.observe(this) { isLoading ->
            if (isLoading) {
                progressBar.visibility = VISIBLE
            } else {
                progressBar.visibility = GONE
            }
        }
    }

    private fun observeErrors() {
        viewModel.isError.observe(this) { isError ->
            if (isError) {
                pussyImage.setImageResource(R.drawable.error_pussy)
                pussyBreed.text = getString(R.string.sadness)
                AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage(R.string.error_message)
                    .setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .create()
                    .show()
            }
        }
    }

    private fun observePussyModel() {
        viewModel.pussy.observe(this) { pussy ->
            pussyImage.load(pussy.url)
            pussyBreed.text = pussy.breeds[0].name
        }
    }

    private fun loadPussyData() {
        lifecycleScope.launch {
            viewModel.loadPussyData()
        }
    }
}