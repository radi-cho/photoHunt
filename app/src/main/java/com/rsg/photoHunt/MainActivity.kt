package com.rsg.photoHunt

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.content.res.Resources
import android.widget.Toast
import android.widget.AdapterView
import android.widget.GridView
import com.rsg.photoHunt.levels.LoadJSONFromAsset

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // Registrer activity
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Customize the title
        title = "Pick a level"

        // Initialize GridView
        val gridview: GridView = findViewById(R.id.gridview)
        gridview.adapter = ImageAdapter(this)

        gridview.onItemClickListener =
                AdapterView.OnItemClickListener { _, _, position, _ ->
                    val intent = Intent(this, LevelActivity::class.java)
                    val message: String = position.toString()
                    intent.putExtra("LEVEL", message)
                    startActivityForResult(intent, 1)
                    Toast.makeText(this, "$position", Toast.LENGTH_SHORT).show()
                }
    }
}

// Add Int.dpToPx which converts dimensions to pixels for more responsive word
fun Int.dpToPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()

class ImageAdapter(private val mContext: Context) : BaseAdapter() {

    override fun getCount(): Int = LoadJSONFromAsset(mContext).getLevelsArray().length()

    override fun getItem(position: Int): Any? = null

    override fun getItemId(position: Int): Long = 0L

    // create a new ImageView for each item referenced by the Adapter
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val thumbnail = R.drawable.ic_launcher_background

        val imageView: ImageView
        if (convertView == null) {
            imageView = ImageView(mContext)
            imageView.layoutParams = ViewGroup.LayoutParams(140.dpToPx(), 120.dpToPx())
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            imageView.setPadding(5, 5, 5, 5)
        } else {
            imageView = convertView as ImageView
        }

        imageView.setImageResource(thumbnail)
        return imageView
    }
}
