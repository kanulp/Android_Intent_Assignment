package com.kanulp.assignment1_intent

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MyBrowserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_browser)

        val intent = intent
        if (intent.data!!.host!!.contains("humber")) {
            val urlTV = findViewById<TextView>(R.id.tv_url)
            urlTV.text = "URL: " + intent.data.toString()
        }
    }
}