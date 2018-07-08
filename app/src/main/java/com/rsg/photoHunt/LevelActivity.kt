package com.rsg.photoHunt

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import java.io.IOException
import java.nio.charset.StandardCharsets
import org.json.JSONObject


class LevelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level)

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

        val initialObject = JSONObject(loadJSONFromAsset())
        val levels = initialObject.getJSONArray("levels")
        val levelItem: String = levels
                .getJSONObject(incomingLevelId)
                .getString("item")

        val levelCount: TextView = findViewById(R.id.levelCount) as TextView
        levelCount.text = levelItem
    }

    fun loadJSONFromAsset(): String {
        var json: String? = null
        try {
            val `is` =
                    assets.open("levels.json")
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            json = String(buffer, StandardCharsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return ""
        }

        return json
    }

}
