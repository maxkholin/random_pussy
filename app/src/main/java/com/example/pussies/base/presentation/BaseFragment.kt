package com.example.pussies.base.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pussies.base.presentation.model.Navigation
import javax.inject.Inject

abstract class BaseFragment : Fragment() {
//    private lateinit var baseViewModel: BaseViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        baseViewModel.navigate.observe(viewLifecycleOwner) { navigate ->
//            when (navigate) {
//                Navigation.Pop -> findNavController().popBackStack()
//                is Navigation.To -> findNavController().navigate(navigate.direction)
//            }
//        }
    }

}