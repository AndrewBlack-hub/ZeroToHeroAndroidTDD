package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val titleTextView = findViewById<TextView>(R.id.titleTextView)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val actionButton = findViewById<Button>(R.id.actionButton)

        UiState.Initial.apply(titleTextView, actionButton, progressBar)

        actionButton.setOnClickListener {
            UiState.Processing.apply(titleTextView, actionButton, progressBar)
            it.postDelayed({
                UiState.Finished.apply(titleTextView, actionButton, progressBar)
            }, 3000L)
        }
    }
}

interface UiState {

    fun apply(textView: TextView, actionButton: Button, progressBar: ProgressBar)

    object Initial : UiState {
        override fun apply(textView: TextView, actionButton: Button, progressBar: ProgressBar) {
            textView.isVisible = false
            actionButton.isEnabled = true
            progressBar.isVisible = false
        }
    }

    object Processing : UiState {
        override fun apply(textView: TextView, actionButton: Button, progressBar: ProgressBar) {
            actionButton.isEnabled = false
            progressBar.isVisible = true
            textView.isVisible = false
        }
    }

    object Finished : UiState {
        override fun apply(textView: TextView, actionButton: Button, progressBar: ProgressBar) {
            progressBar.isVisible = false
            actionButton.isEnabled = true
            textView.isVisible = true
        }
    }
}