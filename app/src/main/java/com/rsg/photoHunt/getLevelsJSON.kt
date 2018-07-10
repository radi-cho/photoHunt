package com.rsg.photoHunt.levels

import android.content.Context
import com.crashlytics.android.Crashlytics
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.StandardCharsets

class LoadJSONFromAsset(private val context: Context) {
    fun loadJSONFromAsset(): String {
        val json: String?
        try {
            val asset = context.assets.open("levels.json")
            val size = asset.available()
            val buffer = ByteArray(size)
            asset.read(buffer)
            asset.close()
            json = String(buffer, StandardCharsets.UTF_8)
        } catch (e: IOException) {
            Crashlytics.logException(e)
            return ""
        }

        return json
    }

    fun getLevelsArray(): JSONArray {
        val JSONfile = loadJSONFromAsset()
        val initialObject = JSONObject(JSONfile)
        val levels = initialObject.getJSONArray("levels")
        return levels
    }
}