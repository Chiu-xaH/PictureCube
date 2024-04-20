package com.chiuxah.picturecube.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.chiuxah.picturecube.App
import com.chiuxah.picturecube.R
import com.chiuxah.picturecube.logic.dataModel.MainBarItems
import com.chiuxah.picturecube.logic.dataModel.NavigationBarItemData
import com.chiuxah.picturecube.logic.utils.SharePrefs.prefs
import com.chiuxah.picturecube.logic.utils.AndroidVersion
import com.chiuxah.picturecube.ui.CubeTab
import com.chiuxah.picturecube.ui.FunctionTab
import com.chiuxah.picturecube.ui.MainTab
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze
import dev.chrisbanes.haze.hazeChild

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainUI() {
    val navController = rememberNavController()
    val hazeState = remember { HazeState() }
    val switchBlur = prefs.getBoolean("SWITCHBLUR", AndroidVersion.sdkInt >= 32)
    var blur by remember { mutableStateOf(switchBlur) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Column {
                TopAppBar(
                    modifier = Modifier.hazeChild(state = hazeState, blurRadius = App.Blur, tint = Color.Transparent, noiseFactor = 0f),
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = if(blur).50f else 1f),
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = { Text("主界面") },
                )
                Divider()
            }
        },

        bottomBar = {
            Divider()
            NavigationBar(
                containerColor = if(blur) MaterialTheme.colorScheme.primaryContainer.copy(.25f) else ListItemDefaults.containerColor ,
                modifier = Modifier.hazeChild(state = hazeState, blurRadius = App.Blur, tint = Color.Transparent, noiseFactor = 0f)
            ) {
                val items = listOf(
                    NavigationBarItemData(MainBarItems.Main.name,"开始", painterResource(id = R.drawable.image), painterResource(id = R.drawable.image_filled)),
                    NavigationBarItemData(MainBarItems.Function.name,"功能", painterResource(id = R.drawable.hotel_class), painterResource(id = R.drawable.hotel_class_filled)),
                    NavigationBarItemData(MainBarItems.Cube.name,"选项", painterResource(id = R.drawable.deployed_code), painterResource(id = R.drawable.deployed_code_filled)),
                )
                items.forEach { item ->
                    val route = item.route
                    val selected = navController.currentBackStackEntryAsState().value?.destination?.route == route
                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            if (!selected) {
                                navController.navigate(route) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        },
                        label = { Text(text = item.label) },
                        icon = {
                            BadgedBox(badge = {
                            }) { Icon(if(selected)item.filledIcon else item.icon, contentDescription = item.label)}
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(navController = navController, startDestination = MainBarItems.Main.name,modifier = Modifier
            .haze(state = hazeState, backgroundColor = MaterialTheme.colorScheme.surface,)) {
            composable(MainBarItems.Main.name) { MainTab(innerPadding) }
            composable(MainBarItems.Function.name) { FunctionTab(innerPadding) }
            composable(MainBarItems.Cube.name) { CubeTab(innerPadding) }
        }
    }
}