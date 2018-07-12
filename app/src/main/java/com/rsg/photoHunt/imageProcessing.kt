package com.rsg.photoHunt.imageProcessing

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import com.crashlytics.android.Crashlytics
import java.io.InputStream
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.FirebaseVision
import com.google.android.gms.tasks.OnFailureListener


class Util(private val context: Context) {
    fun getBitmapFromAsset(filePath: String): Bitmap {
        val assetManager = context.assets

        val istr: InputStream
        val bitmap: Bitmap
        istr = assetManager.open(filePath)
        bitmap = BitmapFactory.decodeStream(istr)
        return bitmap
    }

    fun labelImage(callback: (m: String) -> Unit) {
        val bitmap: Bitmap = getBitmapFromAsset("photo.jpg")
        val detector = FirebaseVision.getInstance()
                .visionLabelDetector
        val image = FirebaseVisionImage.fromBitmap(bitmap)

        detector.detectInImage(image)
                .addOnSuccessListener {
                    if (it != null && it.isNotEmpty()) {
                        val label = it[0].label
                        callback(label)
                    }
                }
                .addOnFailureListener(
                        object : OnFailureListener {
                            override fun onFailure(e: Exception) {
                                Crashlytics.logException(e)
                            }
                        })
    }
}