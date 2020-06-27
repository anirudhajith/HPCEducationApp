package com.example.android.parallelprocessing

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


class VideoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)


        var youtubePlayerView = findViewById<YouTubePlayerView>(R.id.youtubePlayerView)
        lifecycle.addObserver(youtubePlayerView)
        var videoTitleTextView = findViewById<TextView>(R.id.videoTitleTextView)
        var videoTranscriptTextView = findViewById<TextView>(R.id.videoTranscriptTextView)
        val position = intent.getIntExtra("position", 0)
        val videoTitle = resources.getStringArray(R.array.topicsArray)[position]
        val videoId = resources.getStringArray(R.array.videoIdArray)[position]
        val videoTranscript = resources.getStringArray(R.array.videoTranscriptsArray)[position]
        videoTitleTextView.setText(videoTitle)
        videoTranscriptTextView.setText((videoTranscript))

        youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(videoId, 0f)
            }
        })

    }
}
