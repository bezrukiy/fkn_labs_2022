package practice.effective.MarvelHero

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import practice.effective.MarvelHero.navigation.ChoosingAHero
import practice.effective.MarvelHero.ui.theme.ChooseYourHeroTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChooseYourHeroTheme {
                val appState = rememberAppState()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    ChooseYourHeroNavHost(
                        navController = appState.navController,
                        startDestination = ChoosingAHero.route,
                        appState
                    )
                }
            }
        }
    }
}
