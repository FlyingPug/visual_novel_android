package com.dron.visual_novel.game.options

import kotlinx.serialization.Serializable

/*
    Данный класс предаствляет собой ответ, который пользователь выбирает
    в процессе прохождения визуальной новеллы.
*/

@Serializable
data class Option(
    val text: String,
    val nextSceneId: Int
)