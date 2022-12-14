package com.example.layoutsjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Spa
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.layoutsjetpackcompose.ui.theme.LayoutsJetpackComposeTheme
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LayoutsJetpackComposeTheme {
                LayoutsAppScreen()
            }
        }
    }
}
//Search bar - Modifiers.
@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    TextField(
        value ="",
        onValueChange ={},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search
                , contentDescription ="")
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface
        ),
        placeholder = {
            Text(stringResource(R.string.placeholder_search))
        },
    modifier = modifier
        .fillMaxWidth()
        .heightIn(min = 56.dp)
    )
}
// Align body element - Alignment
@Composable
fun AlignYourBodyElement(
    @DrawableRes drawable : Int,
    @StringRes text : Int,
    modifier:Modifier = Modifier
) {
   Column(
       modifier = modifier,
       horizontalAlignment = Alignment.CenterHorizontally
   ) {
       Image(
           painter = painterResource(drawable) ,
           contentDescription = "",
           contentScale = ContentScale.Crop,
           modifier = Modifier
               .size(88.dp)
               .clip(CircleShape)
       )
       Text(
           text = stringResource(text),
           style = MaterialTheme.typography.h5,
           modifier = Modifier.paddingFromBaseline(
               top = 24.dp, bottom = 8.dp
           )
       )
   }
}
//Favorite collection card - Material Surface(y??zey)
@Composable
fun FavoriteCollectionCard(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier=Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.small, //??ekil
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(192.dp)
        ) {
            Image(
                painter = painterResource(drawable),
                contentDescription = "",
                contentScale = ContentScale.Crop,// K??rpmak
                modifier = Modifier.size(56.dp)
            )
            Text(
                text = stringResource(text),
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}
//Align your body row - Arrangements(D??zenlemeler)
@Composable
fun AlignYourBodyRow(
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ){
        items(alignYourBodyData){item ->
            AlignYourBodyElement(item.drawable, item.text)
        }
    }
}

@Composable
fun FavoriteCollectionsGrid(modifier: Modifier = Modifier)
{
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.height(120.dp)
    ) {
        items(favoriteCollectionsData) { item ->
            FavoriteCollectionCard(
                drawable = item.drawable,
                text = item.text,
                modifier = Modifier.height(56.dp)
            )
        }
    }
}
//Home section - Slot APIs
@Composable
fun HomeSection(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        Text(
            text = stringResource(title).uppercase(Locale.getDefault()),
            style = MaterialTheme.typography.h6,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 8.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }
}
//Home screen - Scrolling
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier
            .verticalScroll(rememberScrollState())
            .padding(vertical = 16.dp)
    ) {
        SearchBar(Modifier.padding(horizontal = 16.dp))
        HomeSection(title = R.string.align_your_body) {
            AlignYourBodyRow()
        }
        HomeSection(title = R.string.favorite_collections) {
            FavoriteCollectionsGrid()
        }
    }
}
//Bottom navigation - Material
@Composable
private fun SootheBottomNavigation(modifier: Modifier = Modifier) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.background,
        modifier = modifier
    ) {
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Spa,
                    contentDescription = null
                )
            },
            label = {
                Text(stringResource(R.string.bottom_navigation_home))
            },
            selected = true,
            onClick = {}
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            },
            label = {
                Text(stringResource(R.string.bottom_navigation_profile))
            },
            selected = false,
            onClick = {}
        )
    }
}
@Composable
fun LayoutsAppScreen() {
    Scaffold(
        bottomBar = { SootheBottomNavigation() }
    ) { padding ->
        HomeScreen(Modifier.padding(padding))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun SearchBarPreview() {
    LayoutsJetpackComposeTheme { SearchBar(Modifier.padding(8.dp)) }
}
@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun AlignYourBodyElementPreview() {
    LayoutsJetpackComposeTheme {
        AlignYourBodyElement(
            drawable = R.drawable.ab1_inversions,
            text = R.string.ab1_inversions,
            modifier = Modifier.padding(8.dp)
        )
    }
}
@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun FavoriteCollectionCardPreview() {
    LayoutsJetpackComposeTheme {
        FavoriteCollectionCard(
            drawable = R.drawable.fc2_nature_meditations,
            text = R.string.fc2_nature_meditations,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun FavoriteCollectionsGridPreview() {
    LayoutsJetpackComposeTheme { FavoriteCollectionsGrid() }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun AlignYourBodyRowPreview() {
    LayoutsJetpackComposeTheme { AlignYourBodyRow() }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun HomeSectionPreview() {
    LayoutsJetpackComposeTheme { HomeSection(title = R.string.align_your_body,Modifier.padding(5.dp), content = { AlignYourBodyRow() })}
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun ScreenContentPreview() {
    LayoutsJetpackComposeTheme { HomeScreen() }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun BottomNavigationPreview() {
    LayoutsJetpackComposeTheme { SootheBottomNavigation(Modifier.padding(top = 24.dp)) }
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun MySoothePreview() {
    LayoutsAppScreen()
}

