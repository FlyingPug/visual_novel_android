package com.dron.visual_novel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dron.visual_novel.game.GameManager
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val jsonString = applicationContext.assets.open("game_scenario.json").bufferedReader().use { it.readText() }
        GameManager.game = Json.decodeFromString(jsonString)

        setContentView(R.layout.activity_main)
    }
}