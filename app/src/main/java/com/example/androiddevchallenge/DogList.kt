/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DogLists(
    dogs: List<DogUiModel>,
    navController: NavController,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Dogs")
                }
            )
        }
    ) {
        Box(Modifier.fillMaxSize()) {
            LazyColumn(Modifier.fillMaxWidth()) {
                val groupedBy = dogs.groupBy { it.breed }

                groupedBy.forEach {
                    stickyHeader {
                        DogHeader(it.key.value)
                    }
                    items(it.value) { dog ->
                        DogItem(dog) {
                            navController.navigate(Route.DOG_DETAIL)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DogHeader(value: String) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxWidth(),
        elevation = 6.dp,
    ) {
        Text(
            text = value,
            style = TextStyle(
                color = MaterialTheme.colors.secondary,
                fontSize = MaterialTheme.typography.body1.fontSize
            ),
            modifier = Modifier.padding(16.dp),
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DogItem(dog: DogUiModel, itemViewDetailClickListener: () -> Unit) {
    val expanded = remember { mutableStateOf(false) }
    val liked = remember { mutableStateOf(false) }
    Box(Modifier.padding(8.dp)) {
        Card {
            Column(
                Modifier
                    .fillMaxWidth()
                    .clickable {
                        expanded.value = !expanded.value
                    }
            ) {
                val painter = when (dog.breed) {
                    DogBreed.GermanShepherd -> {
                        painterResource(id = R.drawable.germen_sheperd)
                    }
                    DogBreed.Husky -> {
                        painterResource(id = R.drawable.husky)
                    }
                    DogBreed.BullDog -> {
                        painterResource(id = R.drawable.bull_dog)
                    }
                    DogBreed.Labrador -> {
                        painterResource(id = R.drawable.labrador)
                    }
                    DogBreed.Poodle -> {
                        painterResource(id = R.drawable.poodle)
                    }
                }
                Image(
                    painter = painter,
                    contentDescription = dog.breed.value,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                    val painterLike = if (liked.value) {
                        painterResource(id = R.drawable.ic_filled_thumb_up)
                    } else {
                        painterResource(id = R.drawable.ic_outline_thumb_up)
                    }
                    Text(text = dog.name, style = MaterialTheme.typography.body1)
                    Image(
                        painter = painterLike,
                        contentDescription = dog.breed.value,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(24.dp)
                            .height(24.dp)
                            .clickable {
                                liked.value = !liked.value
                            },
                        colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface),
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                AnimatedVisibility(visible = expanded.value) {
                    Column(
                        modifier = Modifier.padding(
                            start = 16.dp,
                            end = 16.dp,
                            bottom = 16.dp,
                        )
                    ) {
                        Text(
                            text = dog.detail,
                            style = MaterialTheme.typography.body2,
                            maxLines = 4,
                            overflow = TextOverflow.Ellipsis,
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(horizontalArrangement = Arrangement.SpaceBetween) {
                            Button(onClick = { }, Modifier.width(IntrinsicSize.Max)) {
                                Text(text = "Adopt")
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            Button(
                                onClick = { itemViewDetailClickListener.invoke() },
                                Modifier.width(IntrinsicSize.Max)
                            ) {
                                Text(text = "View Detail")
                            }
                        }
                    }
                }
            }
        }
    }
}
