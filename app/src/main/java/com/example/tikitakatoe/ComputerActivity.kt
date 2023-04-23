package com.example.tikitakatoe

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random
import android.content.Context
import android.content.SharedPreferences



class ComputerActivity : AppCompatActivity() {

    enum class Turn {
        CROSS,
        CIRCLE
    }

    private var firstTurn = Turn.CROSS
    private var currentTurn = Turn.CROSS

    private var crossesScore = 0
    private var circlesScore = 0

    private var boardList = mutableListOf<Button>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_computer)

        val twUsername = findViewById<TextView>(R.id.player_name_textview)
        val preferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val savedUsername = preferences.getString("username", null)
        twUsername.text = savedUsername


        initBoard()
    }

    private fun initBoard() {
        boardList.add(findViewById<Button>(R.id.a1))
        boardList.add(findViewById<Button>(R.id.a2))
        boardList.add(findViewById<Button>(R.id.a3))
        boardList.add(findViewById<Button>(R.id.b1))
        boardList.add(findViewById<Button>(R.id.b2))
        boardList.add(findViewById<Button>(R.id.b3))
        boardList.add(findViewById<Button>(R.id.c1))
        boardList.add(findViewById<Button>(R.id.c2))
        boardList.add(findViewById<Button>(R.id.c3))
    }

    fun boardTapped(view: View) {
        if (view !is Button) {
            return
        } else {
            if (currentTurn == Turn.CIRCLE) {
                addToBoard(view)
            }
            if (checkForWin(CIRCLE.toString())) {
                result("CIRCLES WON!")
                circlesScore++
            }

            if (checkForWin(CROSS. toString())) {
                result("CROSSES WON!")
                crossesScore++
            }

            if (fullBoard()) {
                result("DRAW")
            }
            if (currentTurn == Turn.CROSS) {
                makeComputerMove()
            }
        }
    }


    private fun makeComputerMove() {
        val emptyButtons = mutableListOf<Button>()
        for (button in boardList) {
            if (button.text == "") {
                emptyButtons.add(button)
            }
        }
        if (emptyButtons.isNotEmpty()) {
            val randomButton = emptyButtons[Random.nextInt(emptyButtons.size)]
            randomButton.text = CROSS
            currentTurn = Turn.CIRCLE
            setTurnLabel()

            if (checkForWin(CROSS.toString())) {
                result("CROSSES WON!")
                crossesScore++
            }

            if (fullBoard()) {
                result("DRAW")
            }
        }
    }



    private fun checkForWin(s: String): Boolean {
        //Horizontal Victory
        if (match(findViewById(R.id.a1), s) && match(
                findViewById(R.id.a2),
                s
            ) && match(findViewById(R.id.a3), s)
        ) return true
        if (match(findViewById(R.id.b1), s) && match(
                findViewById(R.id.b2),
                s
            ) && match(findViewById(R.id.b3), s)
        ) return true
        if (match(findViewById(R.id.c1), s) && match(
                findViewById(R.id.c2),
                s
            ) && match(findViewById(R.id.c3), s)
        ) return true
        //Vertical Victory
        if (match(findViewById(R.id.a1), s) && match(
                findViewById(R.id.b1),
                s
            ) && match(findViewById(R.id.c1), s)
        ) return true
        if (match(findViewById(R.id.a2), s) && match(
                findViewById(R.id.b2),
                s
            ) && match(findViewById(R.id.c2), s)
        ) return true
        if (match(findViewById(R.id.a3), s) && match(
                findViewById(R.id.b3),
                s
            ) && match(findViewById(R.id.c3), s)
        ) return true
        //Diagonal Victory
        if (match(findViewById(R.id.a1), s) && match(
                findViewById(R.id.b2),
                s
            ) && match(findViewById(R.id.c3), s)
        ) return true
        if (match(findViewById(R.id.a3), s) && match(
                findViewById(R.id.b2),
                s
            ) && match(findViewById(R.id.c1), s)
        ) return true

        return false
    }

    private fun match(button: Button, symbol: String): Boolean = button.text == symbol

    private fun result(title: String) {
        val message = "\nCircles $circlesScore\n\nCrosses $crossesScore"
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Reset") {

                    _, _ ->
                resetBoard()

            }
            .setCancelable(false)
            .show()

    }

    private fun resetBoard() {
        for (button in boardList) {
            button.text = ""
        }

        if (firstTurn == Turn.CIRCLE) {
            firstTurn = Turn.CROSS
        } else if (firstTurn == Turn.CROSS) {
            firstTurn = Turn.CIRCLE
        }

        currentTurn = firstTurn
        setTurnLabel()
    }

    private fun fullBoard(): Boolean {
        for (button in boardList) {
            if (button.text == "") {
                return false
            }
        }
        return true
    }

    private fun addToBoard(button: Button) {
        if (button.text != "") {
            return
        } else {
            if (currentTurn == Turn.CIRCLE) {
                button.text = CIRCLE
                currentTurn = Turn.CROSS
            } else if (currentTurn == Turn.CROSS) {
                button.text = CROSS
                currentTurn = Turn.CIRCLE
            }
        }
        setTurnLabel()
    }

    private fun setTurnLabel() {
        var turnText = ""
        if (currentTurn == Turn.CROSS) {
            turnText = "Turn $CROSS"
        } else if (currentTurn == Turn.CIRCLE) {
            turnText = "Turn $CIRCLE"
        }
        findViewById<TextView>(R.id.turnTV).text = turnText
    }

    companion object {
        const val CIRCLE = "O"
        const val CROSS = "X"
    }
}