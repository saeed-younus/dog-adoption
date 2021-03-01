package com.example.androiddevchallenge

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
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
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = "Dogs")
        })
    }) {
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
                Row {
                    val painterLike = if (liked.value) {
                        painterResource(id = R.drawable.ic_filled_thumb_up)
                    } else {
                        painterResource(id = R.drawable.ic_outline_thumb_up)
                    }
                    Spacer(modifier = Modifier.width(16.dp))
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
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = dog.name, style = MaterialTheme.typography.body1)
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