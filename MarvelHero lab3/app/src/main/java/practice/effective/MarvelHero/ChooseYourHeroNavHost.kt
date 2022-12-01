package practice.effective.MarvelHero

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import practice.effective.MarvelHero.navigation.ChoosingAHero
import practice.effective.MarvelHero.navigation.ErrorMessage
import practice.effective.MarvelHero.navigation.HeroInfo
import practice.effective.MarvelHero.screens.ChoosingScreen
import practice.effective.MarvelHero.screens.ErrorScreen
import practice.effective.MarvelHero.screens.HeroInfoScreen

@Composable
fun ChooseYourHeroNavHost(
    navController: NavHostController,
    startDestination: String,
    appState: AppState
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(route = ChoosingAHero.route) {
            ChoosingScreen(navController)
        }
        composable(
            route = "${HeroInfo.route}/{${HeroInfo.heroIdArg}}",
            arguments = HeroInfo.arguments,
        ) { navBackStackEntry ->
            val heroId = navBackStackEntry.arguments?.getString(HeroInfo.heroIdArg)
            HeroInfoScreen(onBackClick = { appState.onGoBack() }, heroId)
        }
        composable(route = ErrorMessage.route) {
            ErrorScreen()
        }
    }
}
