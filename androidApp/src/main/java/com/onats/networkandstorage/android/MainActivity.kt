package com.onats.networkandstorage.android

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.tooling.preview.Preview
import com.onats.networkandstorage.SpaceXSDK
import com.onats.networkandstorage.cache.DatabaseDriverFactory
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import androidx.compose.runtime.setValue
import com.onats.networkandstorage.entities.RocketLaunch

class MainActivity : ComponentActivity() {
    val mainScope = MainScope()
    private val kmmSdk = SpaceXSDK(DatabaseDriverFactory(this))
    private var rocketLaunches by mutableStateOf(listOf<RocketLaunch>())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        displayLaunches(true)
        setContent {
            MyApplicationTheme {
                SpaceLaunchScreen(
                    rocketLaunches = rocketLaunches
                )
            }
        }
    }

    private fun displayLaunches(needReload: Boolean) {
        //progressBarView.isVisible = true
        mainScope.launch {
            kotlin.runCatching {
                kmmSdk.getLaunches(needReload)
            }.onSuccess { rocketList ->
                Log.d("TAG", "displayLaunches: $rocketList")
                rocketLaunches = rocketList
            }.onFailure {
                Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_LONG).show()
            }
         //   progressBarView.isVisible = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mainScope.cancel()
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}
