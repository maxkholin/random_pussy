package com.example.pussies.randomPussy

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.pussies.R
import com.example.pussies.base.domain.Pussy
import com.example.pussies.base.presentation.BaseFragment
import com.example.pussies.databinding.RandomPussyBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val TAG = "Pussy Random Pussy"

class RandomPussy : BaseFragment() {

    private lateinit var viewModel: RandomPussyViewModel

    private var _binding: RandomPussyBinding? = null
    private val binding: RandomPussyBinding
        get() = _binding ?: throw RuntimeException("FragmentRandomPussyBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RandomPussyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViewModel()
        observeViewModel()
        setupClickListeners()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(
            this, viewModelFactory
        )[RandomPussyViewModel::class.java]
    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is Loading -> showLoading()
                is Error -> showError()
                is Success -> updateUI(state.pussy)
            }
        }
    }

    private fun updateUI(pussy: Pussy) {
        hideLoading()

        lifecycleScope.launch {
            delay(10)
            with(binding.cardPussy) {
                pussyImage.load(pussy.imageUrl)
                toggleFavorite.setImageResource(
                    getButtonFavoriteImage(pussy.isFavorite)
                )
            }
        }
    }

    private fun showError() {
        hideLoading()

        with(binding.cardPussy) {
            pussyImage.setImageResource(R.drawable.error_pussy)
            toggleFavorite.setImageResource(R.drawable.ic_non_active_heart)
        }
        showAlertDialog()
    }

    private fun hideLoading() {
        binding.skeletonLayout.showOriginal()
        binding.buttonLoadPussy.isEnabled = true
    }

    private fun showLoading() {
        binding.skeletonLayout.showSkeleton()
        binding.buttonLoadPussy.isEnabled = false
    }

    private fun showAlertDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Error")
            .setMessage(R.string.random_pussy_error_message)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    /**
     * TODO в другом фрагменте также юзаю этот метод
     */
    private fun getButtonFavoriteImage(isFavorite: Boolean): Int {
        return if (isFavorite) {
            R.drawable.ic_active_heart
        } else {
            R.drawable.ic_non_active_heart
        }
    }

    private fun setupClickListeners() {
        with(binding) {
            buttonLoadPussy.setOnClickListener {
                viewModel.loadPussyData()
            }

            cardPussy.toggleFavorite.setOnClickListener {
                viewModel.toggleFavoriteStatus()
            }

            favorites.setOnClickListener {
                Log.d(TAG, "favorite click")
                findNavController()
                    .navigate(RandomPussyDirections.toFavorites())
            }

            cardPussy.pussyImage.setOnClickListener {
                val state = viewModel.state.value
                if (state is Success) {
                    findNavController()
                        .navigate(RandomPussyDirections.toDetailedInfo(state.pussy))
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.checkFavorite()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}