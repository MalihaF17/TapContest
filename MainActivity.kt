package com.example.tapcontest

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var scoreA = 0
    private var scoreB = 0

    private lateinit var scoreText: TextView
    private lateinit var btnTeamA: Button
    private lateinit var btnTeamB: Button
    private lateinit var btnReset: Button

    private lateinit var clickSound: MediaPlayer
    private lateinit var winSound: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scoreText = findViewById(R.id.scoreText)
        btnTeamA = findViewById(R.id.btnTeamA)
        btnTeamB = findViewById(R.id.btnTeamB)
        btnReset = findViewById(R.id.btnReset)

        clickSound = MediaPlayer.create(this, R.raw.click_sound)
        winSound = MediaPlayer.create(this, R.raw.win_sound)

        if (savedInstanceState != null) {
            scoreA = savedInstanceState.getInt("scoreA")
            scoreB = savedInstanceState.getInt("scoreB")
            Log.d("TapContest", "Score restored: $scoreA - $scoreB")
        }

        updateScore()

        btnTeamA.setOnClickListener {
            clickSound.start()
            scoreA++
            updateScore()
            Log.d("TapContest", "Team A button clicked. Score: $scoreA - $scoreB")
        }

        btnTeamB.setOnClickListener {
            clickSound.start()
            scoreB++
            updateScore()
            Log.d("TapContest", "Team B button clicked. Score: $scoreA - $scoreB")
        }

        btnReset.setOnClickListener {
            clickSound.start()
            scoreA = 0
            scoreB = 0
            updateScore()
            Log.d("TapContest", "Reset button clicked. Score reset.")
        }
    }

    private fun updateScore() {
        scoreText.text = getString(R.string.score_text, scoreA, scoreB)

        if (scoreA == 15) {
            winSound.start()
            Log.d("TapContest", "Team A wins!")
        }

        if (scoreB == 15) {
            winSound.start()
            Log.d("TapContest", "Team B wins!")
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("scoreA", scoreA)
        outState.putInt("scoreB", scoreB)
        Log.d("TapContest", "Score saved: $scoreA - $scoreB")
    }

    override fun onDestroy() {
        super.onDestroy()
        clickSound.release()
        winSound.release()
    }
}