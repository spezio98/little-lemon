package com.example.littlelemon.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.littlelemon.R
import com.example.littlelemon.data.AppRepository
import com.example.littlelemon.data.local.MenuItemLocal
import com.example.littlelemon.navigation.Destinations
import com.example.littlelemon.presentation.viewmodel.HomeViewModel
import com.example.littlelemon.ui.theme.Primary
import com.example.littlelemon.ui.theme.Secondary

@Composable
fun Home(
    modifier: Modifier,
    navController: NavHostController,
    appRepository: AppRepository
) {
    val homeViewModel: HomeViewModel = viewModel {
        HomeViewModel(
            appRepository
        )
    }

    val menuItems = homeViewModel.menuData.collectAsStateWithLifecycle().value
    var searchPhrase by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("") }
    val menuCategories = menuItems.map {
        it.category
    }.sorted().distinct()
    val filteredMenuItems = if (searchPhrase.isNotBlank() || selectedCategory.isNotBlank()) {
        menuItems
            .filter { it.title.contains(searchPhrase, ignoreCase = true) }
            .filter { it.category.equals(selectedCategory, ignoreCase = true) }
    } else {
        menuItems
    }

    Column(
        //verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        Toolbar(
            modifier = modifier,
            navController = navController)
        HeroSection(
            searchPhrase = searchPhrase,
            onSearchPhraseChange = { searchPhrase = it })
        MenuItems(
            selectedCategory = selectedCategory,
            menuCategories = menuCategories,
            menuItems = filteredMenuItems,
            onMenuCategoryClicked = { category ->
                if(category == selectedCategory)
                    selectedCategory = ""
                else
                    selectedCategory = category
            }
        )

    }
}

@Composable
fun Toolbar(modifier: Modifier, navController: NavHostController) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        Spacer(modifier = Modifier.weight(0.25f))
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little Lemon Logo",
            modifier = Modifier
                .weight(0.5f, true)
                .height(50.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Profile image",
            alignment = Alignment.CenterEnd,
            modifier = Modifier
                .weight(0.25f)
                .size(40.dp)
                .clip(CircleShape)
                .clickable {
                    navController.navigate(Destinations.Profile.route)
                }
        )
    }
}

@Composable
fun HeroSection(searchPhrase: String, onSearchPhraseChange: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Primary)
            .padding(horizontal = 15.dp)
    ) {
        Text(
            text = stringResource(R.string.home_hero_section_title),
            color = Secondary,
            style = MaterialTheme.typography.titleLarge
        )
        Row(
            modifier = Modifier
                .padding(vertical = 5.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ){
            Column(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .weight(0.60f)
            ) {
                Text(
                    text = stringResource(R.string.home_hero_section_subtitle),
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 15.dp)
                )
                Text(
                    text = stringResource(R.string.home_hero_section_description),
                    color = Color.White,
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Image(
                painter = painterResource(id = R.drawable.hero_image),
                contentDescription = "Hero Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp)
                    .clip(
                        RoundedCornerShape(16.dp)
                    )
            )

        }
        TextField(
            value = searchPhrase,
            onValueChange = onSearchPhraseChange,
            placeholder = { Text(stringResource(R.string.searchbar_placeholder)) },
            leadingIcon = { Icon( imageVector = Icons.Default.Search, contentDescription = "") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp),
        )
    }
}

@Composable
fun MenuCategoriesSection(
    selectedCategory: String,
    menuCategories: List<String>,
    onMenuCategoryClicked: (String) -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "order for delivery!".uppercase(),
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier
                .fillMaxWidth()
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(vertical = 15.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(menuCategories) { index, category ->
                MenuCategory(
                    category = category,
                    selected = selectedCategory.equals(category, ignoreCase = true),
                    onMenuCategoryClicked = onMenuCategoryClicked
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMenuCategoriesSection() {
    MenuCategoriesSection(
        "Starters",
        listOf("Starters", "Mains", "Desserts", "Beverages")
    ) {}
}

@Composable
fun MenuCategory(
    category: String,
    selected: Boolean,
    onMenuCategoryClicked: (String) -> Unit,
    modifier: Modifier = Modifier
){
    Text(
        text = category,
        style = MaterialTheme.typography.labelMedium,
        color = Primary,
        fontWeight = FontWeight.Bold,
        modifier = modifier
            .wrapContentHeight()
            .background(
                color = if(selected) Secondary else Color.LightGray,
                shape = MaterialTheme.shapes.medium
            )
            .clickable{ onMenuCategoryClicked(category)}
            .padding(vertical = 6.dp, horizontal = 10.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewMenuCategory() {
    MenuCategory(
        category = "Starters",
        onMenuCategoryClicked = {},
        selected = false)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MenuItems(
    selectedCategory: String,
    menuCategories: List<String>,
    menuItems: List<MenuItemLocal>,
    onMenuCategoryClicked: (String) -> Unit
){
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            MenuCategoriesSection(
                selectedCategory = selectedCategory,
                menuCategories = menuCategories,
                onMenuCategoryClicked = onMenuCategoryClicked
            )
        }
        itemsIndexed(menuItems) { index, item ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)) {
                HorizontalDivider(
                    color = Color.LightGray,
                    thickness = if (index == 0) 1.dp else 0.5.dp
                )
                MenuItem(item = item, modifier = Modifier.padding(vertical = 8.dp))
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItem(
    item: MenuItemLocal,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier
        .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.weight(0.60f)
        ) {
            Text(
                text = item.title,
                color = Color.Black,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = item.description,
                color = Color.Black,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = item.price.toString() + "$",
                style = MaterialTheme.typography.bodyLarge,
                color = Primary,
                fontWeight = FontWeight.Bold
            )
        }
        GlideImage(
            model = item.image,
            contentDescription = item.title,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewMenuItem() {
    MenuItem(
        item = MenuItemLocal(
            id = 1,
            title = "Greek Salad",
            description = "The famous greek salad of crispy lettuce, peppers, olives, our",
            price = "12.99",
            image = "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/grilledFish.jpg?raw=true",
            category = "starters"
        )
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewHeroSection() {
    HeroSection(
        searchPhrase = "",
        onSearchPhraseChange = {}
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewToolbar() {
    Toolbar(
        modifier = Modifier,
        navController = NavHostController(LocalContext.current)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewHome() {
    Home(
        navController = NavHostController(LocalContext.current),
        appRepository = AppRepository.getInstance(LocalContext.current),
        modifier = Modifier
    )
}