package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    private var state: State? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val linearLayout: LinearLayout = findViewById(R.id.rootLayout)
        val titleTextView: TextView = findViewById(R.id.titleTextView)
        val removeButton: Button = findViewById(R.id.removeButton)

        removeButton.setOnClickListener {
            state = State.Removed
            state?.apply(linearLayout, titleTextView)
        }

        savedInstanceState?.let {
            state = it.getSerializable("key") as State
            state?.apply(linearLayout, titleTextView)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("key", state)
    }
}

interface State : Serializable {

    fun apply(linearLayout: LinearLayout, textView: TextView)
    object Initial : State {
        override fun apply(linearLayout: LinearLayout, textView: TextView) = Unit
    }

    object Removed : State {
        override fun apply(linearLayout: LinearLayout, textView: TextView) {
            linearLayout.removeView(textView)
        }
    }
}