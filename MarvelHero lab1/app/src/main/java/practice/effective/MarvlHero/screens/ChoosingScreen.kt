package practice.effective.MarvlHero.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.LazyListSnapperLayoutInfo
import dev.chrisbanes.snapper.rememberLazyListSnapperLayoutInfo
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior
import practice.effective.MarvelHero.R
import practice.effective.MarvlHero.ui.components.HeroCard
import practice.effective.MarvlHero.viewmodels.HeroesViewModel

@OptIn(ExperimentalSnapperApi::class)
@Composable
internal fun ChoosingScreen() {
    val heroesViewModel = HeroesViewModel()
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(R.drawable.marvel),
            contentDescription = "",
            modifier = Modifier
                .padding(vertical = 5.dp)
                .size(150.dp),
        )
        Text(
            text = stringResource(id = R.string.choose_your_hero),
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            color = Color.White
        )

        val lazyListState = rememberLazyListState()
        val layoutInfo: LazyListSnapperLayoutInfo = rememberLazyListSnapperLayoutInfo(lazyListState)
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            state = lazyListState,
            flingBehavior = rememberSnapperFlingBehavior(lazyListState),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val items = heroesViewModel.getHeroesList().toList().shuffled()
            items(items) { item ->
                HeroCard(item, items.indexOf(item), layoutInfo)
            }
        }
    }
}
