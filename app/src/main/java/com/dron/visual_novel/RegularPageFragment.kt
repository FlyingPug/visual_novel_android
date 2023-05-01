package com.dron.visual_novel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.dron.visual_novel.databinding.FragmentRegularPageBinding
import com.dron.visual_novel.game.GameManager

class RegularPageFragment : Fragment() {

    private val binding by lazy { FragmentRegularPageBinding.inflate(layoutInflater) }

    private companion object {
        const val NAME_PLACE = "{username}"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val sceneId = arguments?.let { RegularPageFragmentArgs.fromBundle(it).sceneId }

        val navController = findNavController()

        val scene = GameManager.game?.Scenes?.firstOrNull { it.id == sceneId } ?: run{
            navController.navigate(R.id.finalPageFragment)
            return binding.root
        }

        val drawableId = resources.getIdentifier(
            scene.backgroundImageId,
            "drawable",
            requireContext().packageName
        )
        binding.mainBackground.setImageResource(drawableId)

        var dialogue = scene.dialogue

        val name = arguments?.let { RegularPageFragmentArgs.fromBundle(it).name }

        if (name != null) dialogue = dialogue.replace(NAME_PLACE, name)
        binding.dialogueText.text = dialogue

        val options = scene.choices
        for (option in options) {

            val buttonLayout = (LayoutInflater.from(context)
                .inflate(R.layout.option_button, binding.buttonsContainer, false) as Button).also {
                it.text = option.text

                it.setOnClickListener {
                    val action = RegularPageFragmentDirections.actionRegularPageFragmentSelf()
                    action.sceneId = option.nextSceneId
                    navController.navigate(action)
                }
            }

            buttonLayout.text = option.text

            buttonLayout.setOnClickListener {
                val action = RegularPageFragmentDirections.actionRegularPageFragmentSelf()
                action.sceneId = option.nextSceneId
                navController.navigate(action)
            }

            binding.buttonsContainer.addView(buttonLayout)
        }

        return binding.root
    }
}