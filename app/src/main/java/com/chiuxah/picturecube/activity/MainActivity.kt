package com.chiuxah.picturecube.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.chiuxah.picturecube.ui.main.MainUI
import com.chiuxah.picturecube.ui.theme.PictureCubeTheme
import com.chiuxah.picturecube.ui.utils.TransparentSystemBars

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            PictureCubeTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    TransparentSystemBars()
                    MainUI()
                }
            }
        }
    }
}