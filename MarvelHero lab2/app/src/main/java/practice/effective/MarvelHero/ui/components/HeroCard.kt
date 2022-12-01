package practice.effective.MarvelHero.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.LazyListSnapperLayoutInfo
import practice.effective.MarvelHero.AppState
import practice.effective.MarvelHero.models.Hero
import practice.effective.MarvelHero.navigation.HeroInfo

@OptIn(ExperimentalSnapperApi::class)
@Composable
internal fun HeroCard(
    hero: Hero,
    itemIndex: Int,
    layoutInfo: LazyListSnapperLayoutInfo,
    appState: AppState,
    modifier: Modifier = Modifier,
) {
    val cardModifier = if (itemIndex == layoutInfo.currentItem?.index) modifier.size(
        width = 350.dp,
        height = 525.dp
    )
    else modifier.size(width = 300.dp, height = 450.dp)

    Card(modifier = cardModifier
        .clickable(onClick = { appState.navigateSingleTopTo("${HeroInfo.route}/${hero.id}") })
    ) {
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(
                LocalContext.current
            )
                .data(hero.imageUrl)
                .size(Size.ORIGINAL).build()
        )

        when (painter.state) {
            is AsyncImagePainter.State.Loading -> {
                CircularProgressIndicator()
            }
            is AsyncImagePainter.State.Error -> {
                Text(text = "Oops something went wrong. Try again!")
            }
            else -> {
                AsyncImage(
                    model = painter.request.data,
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
            }
        }

        Box(contentAlignment = Alignment.BottomStart) {
            Text(
                modifier = modifier.padding(10.dp),
                text = hero.name,
                style = MaterialTheme.typography.h2,
            )
        }
    }
}
