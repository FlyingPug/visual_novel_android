package com.dron.visual_novel.game.scenes

import com.dron.visual_novel.game.options.Option
import kotlinx.serialization.Serializable

/*
    Данный класс будет отвечать за содержимое текущего экрана,
    P.S возможно часть данных параметров не будет использавана
 */

@Serializable
data class Scene(
    val id: Int,
    val backgroundImageId: String,
    val dialogue: String,
    val choices: List<Option>
)