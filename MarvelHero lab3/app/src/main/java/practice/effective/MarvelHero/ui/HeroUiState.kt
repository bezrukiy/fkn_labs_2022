package practice.effective.MarvelHero.ui

import practice.effective.MarvelHero.models.Hero

sealed class HeroUiState {
    object Empty : HeroUiState()
    object Loading : HeroUiState()
    data class HeroesData(val heroesData: MutableList<Hero> = mutableListOf()) : HeroUiState()
}
