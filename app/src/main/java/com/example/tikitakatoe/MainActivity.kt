package com.example.tikitakatoe

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonPlay = findViewById<Button>(R.id.button_play)
        val buttonSettings = findViewById<Button>(R.id.button_settings)
        val buttonQuit = findViewById<Button>(R.id.button_quit)

        val preferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val username = preferences.getString("username", "Player")

        buttonPlay.setOnClickListener {
            // Start a new activity for the game
            val intent = Intent(this, PlayActivity::class.java)
            startActivity(intent)
        }

        buttonSettings.setOnClickListener {
            // Show settings
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)

        }

        buttonQuit.setOnClickListener {
            // Exit the app
            finishAffinity()
        }
    }



}