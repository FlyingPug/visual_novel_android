package com.dron.visual_novel.game

import com.dron.visual_novel.game.scenes.Scene
import kotlinx.serialization.Serializable

@Serializable
data class Game(
    val currentSceneId: Int = 0,
    val Scenes: List<Scene>
)