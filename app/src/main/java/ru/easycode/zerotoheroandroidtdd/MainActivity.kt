package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    private var state: State = State.Initial
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val linearLayout: LinearLayout = findViewById(R.id.rootLayout)
        val titleTextView: TextView = findViewById(R.id.titleTextView)
        val removeButton: Button = findViewById(R.id.removeButton)

        removeButton.setOnClickListener {
            state = State.Removed
            state.apply(linearLayout = linearLayout, textView = titleTextView, button = removeButton)
        }

        savedInstanceState?.let {
            state = it.getSerializable("key") as State
            state.apply(linearLayout, titleTextView, removeButton)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("key", state)
    }
}

interface State : Serializable {
    fun apply(linearLayout: LinearLayout, textView: TextView, button: Button)

    object Initial : State {
        override fun apply(linearLayout: LinearLayout, textView: TextView, button: Button) = Unit
    }

    object Removed : State {
        override fun apply(linearLayout: LinearLayout, textView: TextView, button: Button) {
            linearLayout.removeView(textView)
            button.isEnabled = false
        }
    }
}