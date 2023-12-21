package com.dicoding.etrash.ui.screen.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.dicoding.etrash.R
import com.dicoding.etrash.model.Category
import com.dicoding.etrash.model.bottombar.BottomBar
import com.dicoding.etrash.navigation.Screen
import com.dicoding.etrash.ui.components.ApaItuItem
import com.dicoding.etrash.ui.components.CategoryItem
import com.dicoding.etrash.ui.components.HomeSection
import com.dicoding.etrash.ui.components.dummyApaItuItem


@Composable
fun HomeScreen(navController: NavHostController) {
    val viewModel: HomeViewModel = viewModel()
    var selectedCategory by remember { mutableStateOf<Screen?>(null) }

    val categories by viewModel.categories.collectAsState()

    Scaffold(
        bottomBar = {
            BottomBar(
                navController = navController
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            Banner()
            HomeSection(
                title = stringResource(R.string.section_category),
                content = {
                    CategoryRow(categories) { category -> selectedCategory = getScreenForCategory(category) }
                }
            )
            HomeSection(
                title = stringResource(R.string.section_apa_itu),
                content = {
                    ApaItu(listApaItu = dummyApaItuItem, navController = navController) }
            )

            selectedCategory?.let { screen ->
                navController.navigate(screen.route)
            }
        }
    }
}

@Composable
fun CategoryRow(categories: List<Category>, onClick: (Category) -> Unit) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(categories) { category ->
            CategoryItem(category) {
                val screen = getScreenForCategory(category)
                onClick(category)
                Log.d("Navigation", "Navigating to ${screen.route}")
            }
        }
    }
}

fun getScreenForCategory(category: Category): Screen {
    return when (category.textCategory) {
        R.string.category_location -> Screen.Location
        R.string.category_scan -> Screen.Camera
        R.string.category_recycle -> Screen.Recycle
        R.string.category_transaction -> Screen.Transaction
        else -> throw IllegalArgumentException("Unknown category: ${category.textCategory}")
    }
}

@Composable
fun Banner() {
    Box {
        Image(
            painter = painterResource(R.drawable.backgroundhome),
            contentDescription = "Banner Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.height(160.dp)
        )
    }
}

@Composable
fun ApaItu(listApaItu: List<ApaItuItem>, navController: NavHostController) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(listApaItu) { item ->
            ApaItuItemCard(item = item, navController = navController)
        }
    }
}

@Composable
fun ApaItuItemCard(item: ApaItuItem, navController: NavHostController) {
    Column(
        modifier = Modifier
            .clickable {
                navigateToScreen(item, navController)
            }
    ) {
        Image(
            painter = painterResource(id = item.image),
            contentDescription = item.title,
            modifier = Modifier
                .size(120.dp)
                .clip(shape = RoundedCornerShape(4.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = item.title,
            style = typography.titleMedium,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

private fun navigateToScreen(item: ApaItuItem, navController: NavHostController) {
    when (item.title) {
        "Apa Itu E-Trash?" -> navController.navigate(Screen.PosterEtrash.route)
//        "Apa Itu Sampah?" -> navController.navigate(Screen.PosterSampah.route)
//        "Apa Itu Reduce-Reuse-Recycle?" -> navController.navigate(Screen.PosterReduceReuseRecycle.route)
//        "Apa itu Uang?" -> navController.navigate(Screen.PosterUang.route)
    }
}