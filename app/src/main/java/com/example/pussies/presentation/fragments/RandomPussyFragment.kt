package com.example.pussies.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.pussies.R
import com.example.pussies.databinding.FragmentRandomPussyBinding
import com.example.pussies.presentation.PussyApp
import com.example.pussies.presentation.viewmodel.MainViewModel
import com.example.pussies.presentation.viewmodel.ViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

class RandomPussyFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as PussyApp).appComponent
    }

    private var _binding: FragmentRandomPussyBinding? = null
    private val binding: FragmentRandomPussyBinding
        get() = _binding ?: throw RuntimeException("FragmentRandomPussyBinding is null")

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRandomPussyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        observeViewModel()

        setupButtonsClickListener()
    }

    private fun setupButtonsClickListener() {
        with(binding) {
            buttonLoadPussy.setOnClickListener {
                loadPussyData()
            }
            buttonToggleFavorite.setOnClickListener {
                toggleFavoriteStatus()
            }

            buttonFavorites.setOnClickListener {
                launchFavoriteFragment()
            }
        }
    }

    private fun launchFavoriteFragment() {
        findNavController().navigate(
            R.id.action_randomPussyFragment_to_favoritePussiesFragment
        )
    }

    private fun observeViewModel() {
        observeLoading()
        observeErrors()
        observePussyModel()
    }

    private fun observeLoading() {
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) VISIBLE else GONE
        }
    }

    private fun observeErrors() {
        viewModel.isError.observe(viewLifecycleOwner) { isError ->
            if (isError) {
                with(binding) {
                    ivPussy.setImageResource(R.drawable.error_pussy)
                    pussyBreed.text = getString(R.string.sadness)
                }
                showAlertDialog()
            }
        }
    }

    private fun showAlertDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Error")
            .setMessage(R.string.error_message)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    private fun observePussyModel() {
        viewModel.pussy.observe(viewLifecycleOwner) { pussy ->

            with(binding) {
                ivPussy.load(pussy.imageUrl)

                pussyBreed.text = pussy.breedName

                buttonToggleFavorite.setImageResource(
                    getButtonFavoriteImage(pussy.isFavorite)
                )
            }
        }
    }

    private fun getButtonFavoriteImage(isFavorite: Boolean): Int {
        return if (isFavorite) {
            R.drawable.acive_heart
        } else {
            R.drawable.not_active_heart
        }
    }

    private fun loadPussyData() {
        lifecycleScope.launch {
            viewModel.loadPussyData()
        }
    }

    private fun toggleFavoriteStatus() {
        lifecycleScope.launch {
            viewModel.toggleFavoriteStatus()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}