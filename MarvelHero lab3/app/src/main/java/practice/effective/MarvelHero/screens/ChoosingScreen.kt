package practice.effective.MarvelHero.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.LazyListSnapperLayoutInfo
import dev.chrisbanes.snapper.rememberLazyListSnapperLayoutInfo
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior
import practice.effective.MarvelHero.R
import practice.effective.MarvelHero.ui.HeroUiState
import practice.effective.MarvelHero.ui.components.HeroCard
import practice.effective.MarvelHero.viewmodels.HeroesViewModel

@OptIn(ExperimentalSnapperApi::class)
@Composable
fun ChoosingScreen(
    navController: NavHostController,
    heroesViewModel: HeroesViewModel = viewModel(),
    modifier: Modifier = Modifier,
) {
    val lazyListState = rememberLazyListState()
    val layoutInfo: LazyListSnapperLayoutInfo = rememberLazyListSnapperLayoutInfo(lazyListState)

    heroesViewModel.getHeroesList(navController)
    val state = heroesViewModel.state.collectAsState()

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(R.drawable.marvel),
            contentDescription = "",
            modifier = modifier
                .padding(vertical = 5.dp)
                .size(150.dp),
        )
        Text(
            text = stringResource(id = R.string.choose_your_hero),
            style = MaterialTheme.typography.h1,
        )
        HeroesLazyList(navController, lazyListState, layoutInfo, state.value)
    }
}

@OptIn(ExperimentalSnapperApi::class)
@Composable
fun HeroesLazyList(
    navController: NavHostController,
    lazyListState: LazyListState,
    layoutInfo: LazyListSnapperLayoutInfo,
    state: HeroUiState,
    modifier: Modifier = Modifier,
) {
    when (state) {
        is HeroUiState.Loading -> {
            Box {
                CircularProgressIndicator(
                    modifier = modifier
                        .size(20.dp)
                        .align(Alignment.Center)
                )
            }
        }

        is HeroUiState.Empty -> {
            Box {
                CircularProgressIndicator(
                    modifier = modifier
                        .size(20.dp)
                        .align(Alignment.Center)
                )
            }
        }

        is HeroUiState.HeroesData -> {
            val items = state.heroesData
            LazyRow(
                modifier = modifier
                    .fillMaxSize(),
                state = lazyListState,
                flingBehavior = rememberSnapperFlingBehavior(lazyListState),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(15.dp),
            ) {
                items(items) { item ->
                    HeroCard(item, items.indexOf(item), layoutInfo, navController)
                }
            }
        }
    }
}
