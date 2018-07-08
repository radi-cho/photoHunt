package levels

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.StandardCharsets

class LoadJSONFromAsset(private val context: Context) {
    fun loadJSONFromAsset(): String {
        val json: String?
        try {
            val `is` = context.assets.open("levels.json")
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

    fun getLevelsArray(): JSONArray {
        val JSONfile = loadJSONFromAsset()
        val initialObject = JSONObject(JSONfile)
        val levels = initialObject.getJSONArray("levels")
        return levels
    }
}