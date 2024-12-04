package com.example.pussies.detailedInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.pussies.R
import com.example.pussies.base.domain.Pussy
import com.example.pussies.base.presentation.BaseFragment
import com.example.pussies.databinding.DetailedInfoBinding

class DetailedInfo : BaseFragment() {

    private val args by navArgs<DetailedInfoArgs>()

    private lateinit var viewModel: DetailedInfoViewModel

    private var _binding: DetailedInfoBinding? = null
    private val binding: DetailedInfoBinding
        get() = _binding ?: throw RuntimeException("FragmentDetailedInfoBinding = null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailedInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pussy = args.pussy
        initAndSetupViewModel(pussy)
        observeViewModel()
        setupClickListeners()
    }

    private fun initAndSetupViewModel(pussy: Pussy) {
        viewModel = ViewModelProvider(this, viewModelFactory)[DetailedInfoViewModel::class]
        viewModel.setPussy(pussy)
    }

    private fun observeViewModel() {
        viewModel.pussy.observe(viewLifecycleOwner) { pussy ->
            setupCharacteristic(pussy)

            binding.toggleFavorite.setImageResource(
                getToggleFavoriteImage(pussy.isFavorite)
            )
        }

        viewModel.statsIsOpen.observe(viewLifecycleOwner) { isOpen ->
            binding.statsLinearLayout.visibility = if (isOpen) {
                View.VISIBLE
            } else {
                View.GONE
            }

            binding.stats.setCompoundDrawablesWithIntrinsicBounds(
                0, 0, getButtonShowMore(isOpen), 0
            )
        }
    }

    private fun setupCharacteristic(pussy: Pussy) {
        with(binding) {
            pussyImage.load(pussy.imageUrl)
            pussyBreed.text =
                String.format(getString(R.string.detailed_info_breed_name_s), pussy.breedName)
            breedDescription.text = pussy.description
            weight.text = String.format(getString(R.string.detailed_info_weight_s), pussy.weight)
            temperament.text =
                String.format(getString(R.string.detailed_info_temperament_s), pussy.temperament)
            origin.text = String.format(getString(R.string.detailed_info_origin_s), pussy.origin)
            lifeSpan.text =
                String.format(getString(R.string.detailed_info_life_span_s), pussy.lifeSpan)
            indoor.text = String.format(getString(R.string.detailed_info_indoor_s), pussy.indoor)
            adaptability.text =
                String.format(getString(R.string.detailed_info_adaptability_s), pussy.adaptability)
            affectionLevel.text =
                String.format(
                    getString(R.string.detailed_info_affection_level_s),
                    pussy.affectionLevel
                )
            childFriendly.text =
                String.format(
                    getString(R.string.detailed_info_child_friendly_s),
                    pussy.childFriendly
                )
            catFriendly.text =
                String.format(getString(R.string.detailed_info_cat_friendly_s), pussy.catFriendly)
            dogFriendly.text =
                String.format(getString(R.string.detailed_info_dog_friendly_s), pussy.dogFriendly)
            strangerFriendly.text =
                String.format(
                    getString(R.string.detailed_info_stranger_friendly_s),
                    pussy.strangerFriendly
                )
            energyLevel.text =
                String.format(getString(R.string.detailed_info_energy_level_s), pussy.energyLevel)
            healthIssues.text =
                String.format(getString(R.string.detailed_info_health_issues_s), pussy.healthIssues)
            intelligence.text =
                String.format(getString(R.string.detailed_info_intelligence_s), pussy.intelligence)
            socialNeeds.text =
                String.format(getString(R.string.detailed_info_social_needs_s), pussy.socialNeeds)
            vocalisation.text =
                String.format(getString(R.string.detailed_info_vocalisation_s), pussy.vocalisation)
            wikipediaUrl.text =
                String.format(getString(R.string.detailed_info_wikipedia_url_s), pussy.wikipediaUrl)
            hypoallergenic.text =
                String.format(
                    getString(R.string.detailed_info_hypoallergenic_s),
                    pussy.hypoallergenic
                )
        }
    }

    private fun getToggleFavoriteImage(isFavorite: Boolean): Int {
        return if (isFavorite) {
            R.drawable.ic_active_heart
        } else {
            R.drawable.ic_non_active_heart
        }
    }

    private fun getButtonShowMore(isOpen: Boolean): Int {
        return if (isOpen) {
            R.drawable.ic_expand_less
        } else {
            R.drawable.ic_expand_more
        }
    }

    private fun setupClickListeners() {
        setupButtonShowMore()
        setupToggleFavorite()
        setupButtonBack()
    }

    private fun setupButtonShowMore() {
        binding.stats.setOnClickListener {
            viewModel.toggleStats()
        }
    }

    private fun setupToggleFavorite() {
        binding.toggleFavorite.setOnClickListener {
            viewModel.toggleFavoriteStatus()
        }
    }

    private fun setupButtonBack() {
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}