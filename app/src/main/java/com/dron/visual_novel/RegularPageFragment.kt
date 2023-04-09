package com.dron.visual_novel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.dron.visual_novel.databinding.FragmentRegularPageBinding
import com.dron.visual_novel.game.Game
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


class RegularPageFragment : Fragment() {

    private val binding by lazy { FragmentRegularPageBinding.inflate(layoutInflater) }
    private val namePlace = "{username}"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val sceneId = arguments?.let { RegularPageFragmentArgs.fromBundle(it).sceneId }

        val navController = findNavController()

        val jsonString =
            context?.assets?.open("game_scenario.json")?.bufferedReader().use { it?.readText() }

        val game = jsonString?.let { Json.decodeFromString<Game>(it) }

        val scene =
            game?.Scenes?.firstOrNull { it.id == sceneId }

        if (scene == null) {
            navController.navigate(R.id.finalPageFragment)
            return binding.root
        }

        val drawableId = resources.getIdentifier(
            scene.backgroundImageId,
            "drawable",
            requireContext().packageName
        )
        //view.background = ContextCompat.getDrawable(requireContext(), drawableId)
        binding.mainBackground.setImageResource(drawableId)

        /*val dialogueId =
            resources.getIdentifier(scene?.dialogue, "string", requireContext().packageName)*/
        var dialogue = scene.dialogue//resources.getString(dialogueId)

        val name = arguments?.let { RegularPageFragmentArgs.fromBundle(it).name }

        if (name != null) dialogue = dialogue.replace(namePlace, name)
        binding.dialogueText.text = dialogue

        val options = scene.choices
        for (option in options) {

            val buttonLayout = LayoutInflater.from(context)
                .inflate(R.layout.option_button, binding.buttonsContainer, false) as Button

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