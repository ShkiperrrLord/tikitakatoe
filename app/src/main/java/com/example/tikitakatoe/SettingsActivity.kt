package com.example.tikitakatoe

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        Toast.makeText(this, "Settings activity opened!", Toast.LENGTH_SHORT).show()

        val editTextUsername = findViewById<EditText>(R.id.editTextUsername)
        val buttonSave = findViewById<Button>(R.id.buttonSave)

        // Load the current username and language from SharedPreferences
        val preferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val username = preferences.getString("username", "")

        editTextUsername.setText(username)

        buttonSave.setOnClickListener {
            // Save the new username and language to SharedPreferences
            val newUsername = editTextUsername.text.toString()

            val editor = preferences.edit()
            editor.apply {

                putString("username", newUsername)
                apply()

            }

            Toast.makeText(this, "Settings saved", Toast.LENGTH_SHORT).show()

            // Finish the activity and return to the main menu
            finish()
        }
    }
}
