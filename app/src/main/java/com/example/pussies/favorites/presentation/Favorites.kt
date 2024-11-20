package com.example.pussies.favorites.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.pussies.base.domain.Pussy
import com.example.pussies.base.presentation.PussyApp
import com.example.pussies.base.presentation.viewmodel.ViewModelFactory
import com.example.pussies.databinding.FavoritePussiesBinding
import com.example.pussies.favorites.adapter.PussyAdapter
import javax.inject.Inject

class Favorites : Fragment() {

    private lateinit var viewModel: FavoritesViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as PussyApp).appComponent
    }

    private var _binding: FavoritePussiesBinding? = null
    private val binding: FavoritePussiesBinding
        get() = _binding ?: throw RuntimeException("FragmentFavoritePussiesBinding = null")

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FavoritePussiesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory)[FavoritesViewModel::class]

        val adapter = PussyAdapter(viewModel::removeFromFavorites, ::launchDetailedInfoFragment)
        binding.favoritesRv.adapter = adapter

        observeViewModel(adapter)
        setupButtonBack()
    }

    private fun setupButtonBack() {
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun observeViewModel(adapter: PussyAdapter) {
        viewModel.pussyList.observe(viewLifecycleOwner) { list ->
            if (list.isEmpty()) {
                binding.emptyFavorites.visibility = View.VISIBLE
            } else {
                binding.emptyFavorites.visibility = View.INVISIBLE
            }
            adapter.submitList(list)
        }
    }

    private fun launchDetailedInfoFragment(pussy: Pussy) {
        findNavController()
            .navigate(
                FavoritesDirections.toDetailedInfo(pussy)
            )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}