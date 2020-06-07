package com.example.kotlindemo

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.example.kotlindemo.common.Config
import com.example.kotlindemo.model.VideoModel
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

class YouTubeActivty : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {
    var youTubeView: YouTubePlayerView? = null
    var youTubePlayer: YouTubePlayer? = null
    private val RECOVERY_DIALOG_REQUEST = 1

    override fun onInitializationSuccess(
        p0: YouTubePlayer.Provider?,
        p1: YouTubePlayer?,
        p2: Boolean
    ) {
        youTubePlayer = p1
//        youTubePlayer.setPlayerStateChangeListener(this)
//        player.setOnFullscreenListener(this)
        p1?.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT)

        if (!p2) {
            p1?.loadVideo(Config.YOUTUBE_VIDEO_CODE)
        }

        Handler().postDelayed({
            youTubeView?.refreshDrawableState()
            /*if (btnVisible == 0) {
                        contin2.setVisibility(View.VISIBLE);
                    }*/

            if (!youTubePlayer?.isPlaying()!!) {
                youTubePlayer?.play()
            }
        }, 10)
    }

    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        errorReason: YouTubeInitializationResult?
    ) {
        if (errorReason?.isUserRecoverableError()!!) {
            errorReason?.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show()
        } else {
            val errorMessage = String.format(
                getString(R.string.error_player), errorReason.toString()
            )
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_you_tube_activty)
        youTubeView = findViewById(R.id.youTubeView)
        var bundle = intent.getBundleExtra("BUNDLE")
        var videoModel: VideoModel = bundle.getSerializable("MODEL") as VideoModel
//        play_video(Config.YOUTUBE_VIDEO_CODE)
        play_video(videoModel.videoId)
    }

    fun play_video(id: String) {

        Config.YOUTUBE_VIDEO_CODE = id
        youTubeView?.initialize(Config.DEVELOPER_KEY, this)

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(Config.DEVELOPER_KEY, this)
        }
    }

    private fun getYouTubePlayerProvider(): YouTubePlayer.Provider {
        return findViewById(R.id.youTubeView) as YouTubePlayerView
    }

}
