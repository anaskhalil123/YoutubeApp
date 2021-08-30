package com.a.khalil.youtubeapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.youtube.player.YouTubeStandalonePlayer.createPlaylistIntent
import com.google.android.youtube.player.YouTubeStandalonePlayer.createVideoIntent

class StandaloneActivity : AppCompatActivity(), View.OnClickListener {
    private val btnStartVideo by lazy { findViewById<Button>(R.id.btnStartVideo) }
    private val btnStartPlayList by lazy { findViewById<Button>(R.id.btnStartPlayList) }
    val TAG = "StandaloneActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_standalone)

        btnStartVideo.setOnClickListener(this)
        btnStartPlayList.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        try {
            val intent = when (view!!.id) {
                R.id.btnStartVideo -> createVideoIntent(
                    this,
                    getString(R.string.YOUTUBE_API_KEY),
                    VIDEO, 0, true, false
                )
                R.id.btnStartPlayList -> createPlaylistIntent(
                    this,
                    getString(R.string.YOUTUBE_API_KEY),
                    PLAYLIST, 0, 0, true, false
                )
                else -> throw IllegalArgumentException("Undefined Error")
            }
            startActivity(intent)
        } catch (e: Exception) {
            Log.d(TAG, e.message.toString())
            Toast.makeText(this, "${e.message}", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}