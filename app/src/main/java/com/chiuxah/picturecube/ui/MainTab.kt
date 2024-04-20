package com.chiuxah.picturecube.ui

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.webkit.WebView
import android.widget.ImageView
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat.startActivityForResult
import com.chiuxah.picturecube.App
import com.chiuxah.picturecube.App.Companion.context
import com.chiuxah.picturecube.R
import com.chiuxah.picturecube.activity.SelectPhotoActivity
import java.io.InputStream

@Composable
fun MainTab(innerPadding : PaddingValues) {
    Box(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(innerPadding.calculateTopPadding()))
        val scrollstate = rememberLazyListState()
        val shouldShowAddButton = scrollstate.firstVisibleItemScrollOffset  == 0
        var bitmap : ImageBitmap?  by remember { mutableStateOf(null) }
        bitmap?.let { Image(bitmap = it, contentDescription = "", modifier = Modifier.align(Alignment.Center).padding(horizontal = 15.dp)) }
        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // 处理选中的图片
                val imageUri = result.data?.data
                imageUri?.let { uri ->
                    val imageStream: InputStream? = context.contentResolver.openInputStream(uri)
                    val selectedImage = BitmapFactory.decodeStream(imageStream)
                     bitmap = selectedImage.asImageBitmap()
                }
            }
        }

        // 记住Intent，这样不会在每次重组时都创建新的Intent
        val selectImageIntent = remember {
            Intent(Intent.ACTION_PICK).apply {
                type = "image/*"
            }
        }


        ExtendedFloatingActionButton(
            text = { Text(text = "选择图片") },
            icon = { Icon(Icons.Filled.Add, contentDescription = "") },
            onClick = {
                launcher.launch(selectImageIntent)
            },
            expanded = shouldShowAddButton,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(innerPadding)
                .padding(15.dp)
        )
    }
}