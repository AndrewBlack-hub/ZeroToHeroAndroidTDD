package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var countTextView: TextView? = null
    private val count = Count.Base(2)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countTextView = findViewById(R.id.countTextView)
        val incrementButton: Button = findViewById(R.id.incrementButton)

        incrementButton.setOnClickListener {
            countTextView?.text = count.increment(countTextView?.text.toString())
        }

        savedInstanceState?.let {
            countTextView?.text = it.getString("key")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("key", countTextView?.text.toString())
    }
}

interface Count {
    fun increment(number: String): String
    class Base(private val step: Int) : Count {

        init {
            if (step < 1) throw IllegalStateException("step should be positive, but was $step")
        }

        override fun increment(number: String) = (step + number.toInt()).toString()
    }
}