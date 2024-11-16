package com.example.pussies.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.pussies.R
import com.example.pussies.databinding.FragmentDetailedInfoBinding
import com.example.pussies.presentation.PussyApp
import com.example.pussies.presentation.viewmodel.DetailedInfoViewModel
import com.example.pussies.presentation.viewmodel.ViewModelFactory
import javax.inject.Inject

class DetailedInfoFragment : Fragment() {

    private val args by navArgs<DetailedInfoFragmentArgs>()

    private lateinit var viewModel: DetailedInfoViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as PussyApp).appComponent
    }

    private var _binding: FragmentDetailedInfoBinding? = null
    private val binding: FragmentDetailedInfoBinding
        get() = _binding ?: throw RuntimeException("FragmentDetailedInfoBinding = null")

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailedInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory)[DetailedInfoViewModel::class]

        with(binding) {
            val pussy = args.pussy
            pussyImage.load(pussy.imageUrl)
            breedName.text = String.format(getString(R.string.breed_name_s), pussy.breedName)
            description.text = pussy.description
            weight.text = String.format(getString(R.string.weight_s), pussy.weight)
            temperament.text = String.format(getString(R.string.temperament_s), pussy.temperament)
            origin.text = String.format(getString(R.string.origin_s), pussy.origin)
            lifeSpan.text = String.format(getString(R.string.life_span_s), pussy.lifeSpan)
            indoor.text = String.format(getString(R.string.indoor_s), pussy.indoor)
            adaptability.text =
                String.format(getString(R.string.adaptability_s), pussy.adaptability)
            affectionLevel.text =
                String.format(getString(R.string.affection_level_s), pussy.affectionLevel)
            childFriendly.text =
                String.format(getString(R.string.child_friendly_s), pussy.childFriendly)
            catFriendly.text = String.format(getString(R.string.cat_friendly_s), pussy.catFriendly)
            dogFriendly.text = String.format(getString(R.string.dog_friendly_s), pussy.dogFriendly)
            strangerFriendly.text =
                String.format(getString(R.string.stranger_friendly_s), pussy.strangerFriendly)
            energyLevel.text = String.format(getString(R.string.energy_level_s), pussy.energyLevel)
            healthIssues.text =
                String.format(getString(R.string.health_issues_s), pussy.healthIssues)
            intelligence.text =
                String.format(getString(R.string.intelligence_s), pussy.intelligence)
            socialNeeds.text = String.format(getString(R.string.social_needs_s), pussy.socialNeeds)
            vocalisation.text =
                String.format(getString(R.string.vocalisation_s), pussy.vocalisation)
            wikipediaUrl.text =
                String.format(getString(R.string.wikipedia_url_s), pussy.wikipediaUrl)
            hypoallergenic.text =
                String.format(getString(R.string.hypoallergenic_s), pussy.hypoallergenic)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}