package com.chiuxah.picturecube.activity

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.chiuxah.picturecube.activity.ui.theme.PictureCubeTheme
import com.chiuxah.picturecube.ui.main.MainUI
import com.chiuxah.picturecube.ui.utils.TransparentSystemBars

class SelectPhotoActivity : ComponentActivity() {
    // val takePhoto = 1
    val fromAlbum = 2
    lateinit var imageUri: Uri
    //  lateinit var outputImage: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val it = Intent(Intent.ACTION_OPEN_DOCUMENT)
        it.addCategory(Intent.CATEGORY_OPENABLE)
        it.type = "image/*"
        startActivityForResult(it, 2)
    }
    fun getBitmapFromUri(uri: Uri) = contentResolver.openFileDescriptor(uri, "r")?.use {
        BitmapFactory.decodeFileDescriptor(it.fileDescriptor)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {

            fromAlbum -> {
                //if (resultCode == Activity.RESULT_OK && data != null) {
                data?.data?.let { uri ->
                    val bitmap = getBitmapFromUri(uri)
                    val it = Intent(this,MainActivity::class.java)
                    it.putExtra("Bitmap",bitmap)
                    startActivity(it)
                }
            }

            //  takePhoto -> {
            //if (resultCode == Activity.RESULT_OK) {
            //        val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(imageUri))
            //        iv.setImageBitmap(bitmap)
            //}
        }

    }

}
