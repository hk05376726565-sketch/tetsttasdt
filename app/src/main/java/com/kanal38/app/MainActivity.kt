package com.kanal38.app

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

class MainActivity : Activity() {

    companion object {
        private const val STREAM_URL =
            "https://yayin1.canlitv.fun/canlitv/kanal38.stream/playlist.m3u8"
    }

    private lateinit var logo: ImageView
    private lateinit var playerContainer: FrameLayout
    private lateinit var playerView: PlayerView
    private var player: ExoPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        logo = findViewById(R.id.logo)
        playerContainer = findViewById(R.id.playerContainer)
        playerView = findViewById(R.id.playerView)

        logo.setOnClickListener { startPlayback() }
    }

    private fun startPlayback() {
        if (player != null) return

        logo.visibility = View.GONE
        playerContainer.visibility = View.VISIBLE
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        val exo = ExoPlayer.Builder(this).build()
        exo.addListener(object : Player.Listener {
            override fun onPlayerError(error: PlaybackException) {
                Toast.makeText(
                    this@MainActivity,
                    "Yayın açılamadı, internet bağlantınızı kontrol edin.",
                    Toast.LENGTH_LONG
                ).show()
                stopPlayback()
            }
        })
        playerView.player = exo
        exo.setMediaItem(MediaItem.fromUri(STREAM_URL))
        exo.playWhenReady = true
        exo.prepare()
        player = exo
    }

    private fun stopPlayback() {
        playerView.player = null
        player?.release()
        player = null
        window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        playerContainer.visibility = View.GONE
        logo.visibility = View.VISIBLE
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (player != null) {
            // Yayından logo ekranına dön
            stopPlayback()
        } else {
            finish()
        }
    }

    // Uygulama arka plana alınınca yayın tamamen durur ve kaynaklar serbest bırakılır.
    override fun onStop() {
        super.onStop()
        stopPlayback()
    }

    override fun onDestroy() {
        super.onDestroy()
        player?.release()
        player = null
    }
}
