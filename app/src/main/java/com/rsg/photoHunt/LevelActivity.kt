package com.rsg.photoHunt

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.rsg.photoHunt.levels.LoadJSONFromAsset
import com.rsg.photoHunt.imageProcessing.Util
import com.google.firebase.analytics.FirebaseAnalytics

class LevelActivity : AppCompatActivity() {
    fun setTextView (str: String) {
        val levelInfo: TextView = findViewById(R.id.levelCount)
        levelInfo.text = str
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level)

        val mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)

        val incomingLevelId: Int
        if (savedInstanceState == null) {
            val extras = intent.extras
            if (extras == null) {
                incomingLevelId = 1
            } else {
                incomingLevelId = extras.getString("LEVEL").toInt()
            }
        } else {
            incomingLevelId = (savedInstanceState.getSerializable("LEVEL") as String).toInt()
        }

        val levels = LoadJSONFromAsset(this).getLevelsArray()
        val levelItem: String = levels
                .getJSONObject(incomingLevelId)
                .getString("item")

        Util(this).labelImage(::setTextView)

        mFirebaseAnalytics.logEvent("load_level", null)
        mFirebaseAnalytics.logEvent("load_level_${incomingLevelId + 1}", null)
    }
}
