package com.a.khalil.youtubeapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

const val VIDEO = "3Hgw-KZ7iRY"
const val PLAYLIST = "PLQYjhn7xrRnaHRNU3xUspKQv1wspDJ28O"

class YoutubeActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    private val TAG = "YoutubeActivity"
    private val playerView by lazy { YouTubePlayerView(this) }
    private val DIALOG_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val myLayout =
            layoutInflater.inflate(R.layout.activity_youtube, null) as ConstraintLayout
        setContentView(myLayout)

        playerView.layoutParams = ConstraintLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        playerView.initialize(getString(R.string.YOUTUBE_API_KEY), this)

        myLayout.addView(playerView)
    }

    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider?,
        player: YouTubePlayer?,
        wasRestored: Boolean
    ) {

        if (!wasRestored) {
            player?.loadVideo(VIDEO)
        } else {
            player?.play()
        }
    }

    override fun onInitializationFailure(
        provider: YouTubePlayer.Provider?,
        error: YouTubeInitializationResult?
    ) {
        if (error!!.isUserRecoverableError) {
            error.getErrorDialog(this, DIALOG_REQUEST_CODE).show()
        } else {
            Log.e(TAG, "The error is $error")
        }
    }

    /*(onActivityResult) this method called just if the getErrorDialog() method called */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d(TAG, "result code is $resultCode, request code is $requestCode")

        if (resultCode == 0) {
            playerView.initialize(getString(R.string.YOUTUBE_API_KEY), this)
        }
    }

}