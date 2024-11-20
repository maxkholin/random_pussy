package com.example.pussies.randomPussy

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.pussies.R
import com.example.pussies.base.domain.Pussy
import com.example.pussies.base.presentation.PussyApp
import com.example.pussies.base.presentation.viewmodel.ViewModelFactory
import com.example.pussies.databinding.RandomPussyBinding
import javax.inject.Inject

class RandomPussy : Fragment() {

    private lateinit var viewModel: RandomPussyViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as PussyApp).appComponent
    }

    private var _binding: RandomPussyBinding? = null
    private val binding: RandomPussyBinding
        get() = _binding ?: throw RuntimeException("FragmentRandomPussyBinding is null")

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RandomPussyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel = ViewModelProvider(this, viewModelFactory)[RandomPussyViewModel::class.java]
        observeViewModel()

        setupClickListeners()
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
                launchFavoriteFragment()
            }

            cardPussy.pussyImage.setOnClickListener {
                viewModel.pussy.value.let {
                    if (it != null) {
                        launchDetailedInfoFragment(it)
                    }
                }
            }
        }
    }

    private fun launchFavoriteFragment() {
        findNavController().navigate(
            RandomPussyDirections.toFavorites()
        )
    }

    private fun launchDetailedInfoFragment(pussy: Pussy) {
        findNavController().navigate(
            RandomPussyDirections.toDetailedInfo(pussy)
        )
    }

    private fun observeViewModel() {
        observeLoading()
        observeErrors()
        observePussyModel()
    }

    private fun observeLoading() {
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.skeletonLayout.showSkeleton()
                binding.buttonLoadPussy.isEnabled = false
            } else {
                binding.skeletonLayout.showOriginal()
                binding.buttonLoadPussy.isEnabled = true
            }
        }
    }

    private fun observeErrors() {
        viewModel.isError.observe(viewLifecycleOwner) { isError ->
            if (isError) {
                with(binding.cardPussy) {
                    pussyImage.setImageResource(R.drawable.error_pussy)
                    pussyBreed.text = getString(R.string.random_pussy_sadness)
                    toggleFavorite.setImageResource(R.drawable.ic_non_active_heart)
                }
                showAlertDialog()
            }

            viewModel.resetError()
        }
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

    private fun observePussyModel() {
        viewModel.pussy.observe(viewLifecycleOwner) { pussy ->
            with(binding.cardPussy) {
                Log.d("Pussy", "in Observe")
                pussyImage.load(pussy.imageUrl)

                pussyBreed.text = pussy.breedName

                toggleFavorite.setImageResource(
                    getButtonFavoriteImage(pussy.isFavorite)
                )
            }
            Log.d("Pussy", pussy.isFavorite.toString())
        }
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

//    override fun onResume() {
//        super.onResume()
//        viewModel.checkFavorite()
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}