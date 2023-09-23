package com.onats.networkandstorage.android

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.onats.networkandstorage.entities.RocketLaunch

@[Composable Preview]
fun SpaceLaunchScreen(
    rocketLaunches: List<RocketLaunch> = listOf()
) {
    Scaffold { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues)
        ) {
            items(rocketLaunches.size) {index ->
                LaunchCard(
                    rocketLaunch = rocketLaunches[index],
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
                )
            }
        }
    }
}

@Composable
fun LaunchCard(
    rocketLaunch: RocketLaunch,
    modifier: Modifier
) {
    Card(
        elevation = 10.dp,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Text(
                text = "Launch Name: ${rocketLaunch.missionName}",
                style = TextStyle(
                    fontSize = 16.sp
                ),
                modifier = Modifier.padding(bottom = 12.dp)
            )
            Text(
                text = if(rocketLaunch.launchSuccess == true) "Successful" else "Unsuccessful",
                style = TextStyle(
                    fontSize = 16.sp
                ),
                modifier = Modifier.padding(bottom = 12.dp)
            )
            Text(
                text = "Launch Year: ${rocketLaunch.launchYear}",
                style = TextStyle(
                    fontSize = 16.sp
                ),
                modifier = Modifier.padding(bottom = 12.dp)
            )
            Text(
                text = "Launch Details: ${rocketLaunch.details}",
                style = TextStyle(
                    fontSize = 16.sp
                ),
                modifier = Modifier.padding(bottom = 12.dp)
            )
        }
    }
}