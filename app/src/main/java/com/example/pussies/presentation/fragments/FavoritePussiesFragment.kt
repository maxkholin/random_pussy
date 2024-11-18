package com.example.pussies.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.pussies.databinding.FragmentFavoritePussiesBinding
import com.example.pussies.domain.Pussy
import com.example.pussies.presentation.PussyApp
import com.example.pussies.presentation.adapter.PussyAdapter
import com.example.pussies.presentation.viewmodel.FavoritesViewModel
import com.example.pussies.presentation.viewmodel.ViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritePussiesFragment : Fragment() {

    private lateinit var viewModel: FavoritesViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as PussyApp).appComponent
    }

    private var _binding: FragmentFavoritePussiesBinding? = null
    private val binding: FragmentFavoritePussiesBinding
        get() = _binding ?: throw RuntimeException("FragmentFavoritePussiesBinding = null")

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritePussiesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val removeFromFavorite: (pussyId: String) -> Unit = {
            removePussyFromFavorite(it)
        }

        val launchDetailedInfoFragment: (pussy: Pussy) -> Unit = {
            findNavController()
                .navigate(
                    FavoritePussiesFragmentDirections
                        .actionFavoritePussiesFragmentToDetailedInfoFragment(it)
                )
        }

        val adapter = PussyAdapter(removeFromFavorite, launchDetailedInfoFragment)
        binding.favoritePussies.adapter = adapter

        viewModel = ViewModelProvider(this, viewModelFactory)[FavoritesViewModel::class]
        observeViewModel(adapter)
        setupButtonsClickListener()
    }

    private fun setupButtonsClickListener() {
        binding.home.setOnClickListener {
            launchRandomPussyFragment()
        }
    }

    private fun launchRandomPussyFragment() {
        findNavController().popBackStack()
    }

    private fun observeViewModel(adapter: PussyAdapter) {
        viewModel.pussyList.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }
    }

    private fun removePussyFromFavorite(pussyId: String) {
        lifecycleScope.launch {
            viewModel.removeFromFavorites(pussyId)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}