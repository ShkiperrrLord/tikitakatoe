package com.example.tikitakatoe

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class PlayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

        val buttonPlayer = findViewById<Button>(R.id.play_vs_player_button)
        val buttonComputer = findViewById<Button>(R.id.play_vs_computer_button)

        buttonPlayer.setOnClickListener {
            // Start a new activity for the game
            val intent = Intent(this, PlayerActivity::class.java)
            startActivity(intent)
        }

        buttonComputer.setOnClickListener {
            // Start a new activity for the game
            val intent = Intent(this, ComputerActivity::class.java)
            startActivity(intent)
        }

    }

}