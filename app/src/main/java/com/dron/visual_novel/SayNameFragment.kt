package com.dron.visual_novel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.dron.visual_novel.databinding.FragmentSayNameBinding

class SayNameFragment : Fragment() {


    private val binding by lazy { FragmentSayNameBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val navController = findNavController()

        binding.confirmButton.setOnClickListener {
            val action = SayNameFragmentDirections.actionSayNameFragmentToRegularPageFragment()
            action.name = binding.editTextTextPersonName.text.toString()
            action.sceneId = 3
            navController.navigate(action)
        }

        return binding.root
    }
}