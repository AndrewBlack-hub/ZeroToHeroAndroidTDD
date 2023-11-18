package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {

    private var titleTextView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        titleTextView = findViewById(R.id.titleTextView)
        val hideButton: Button = findViewById(R.id.hideButton)

        hideButton.setOnClickListener {
            titleTextView?.isVisible = false
        }
        savedInstanceState?.let {
            titleTextView?.isVisible = it.getBoolean("key")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("key", titleTextView?.isVisible ?: true)
    }
}