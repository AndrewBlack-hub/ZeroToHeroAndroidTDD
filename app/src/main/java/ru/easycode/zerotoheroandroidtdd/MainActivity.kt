package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        initUi()
    }

    private fun initUi() {
        val layout = LinearLayout(this).apply {
            id = R.id.rootLayout
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
        }
        val textView = TextView(this).apply {
            gravity = Gravity.CENTER
            id = R.id.titleTextView
            text = "I am an Android Developer!"
        }
        val buttonView = Button(this).apply {
            id = R.id.changeButton
            text = "change"
        }
        layout.addView(textView)
        layout.addView(buttonView)
        setContentView(layout)
    }
}