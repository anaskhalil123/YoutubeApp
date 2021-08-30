package com.a.khalil.youtubeapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val btnStartVideo by lazy { findViewById<Button>(R.id.btnStartVideo1) }
    private val btnStandaloneMenu by lazy { findViewById<Button>(R.id.btnStandaloneMenu) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStartVideo.setOnClickListener(this)
        btnStandaloneMenu.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        val intent = when (view?.id) {
            R.id.btnStartVideo1 -> Intent(this, YoutubeActivity::class.java)
            R.id.btnStandaloneMenu -> Intent(this, StandaloneActivity::class.java)
            else -> throw IllegalArgumentException("Undefined button")
        }
        startActivity(intent)
    }
}