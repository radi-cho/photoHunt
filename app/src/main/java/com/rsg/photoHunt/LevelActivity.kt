package com.rsg.photoHunt

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_level.*

class LevelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level)

        val newString: String?
        if (savedInstanceState == null) {
            val extras = intent.extras
            if (extras == null) {
                newString = null
            } else {
                newString = extras.getString("STRING_I_NEED")
            }
        } else {
            newString = savedInstanceState.getSerializable("STRING_I_NEED") as String
        }

        val levelCount: TextView = findViewById(R.id.levelCount) as TextView
        levelCount.text = newString
    }

}
