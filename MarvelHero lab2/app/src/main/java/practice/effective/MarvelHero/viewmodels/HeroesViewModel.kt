package practice.effective.MarvelHero.viewmodels

import androidx.lifecycle.ViewModel
import practice.effective.MarvelHero.models.Hero
import practice.effective.MarvelHero.repositories.HeroesRepository

class HeroesViewModel(repository: HeroesRepository = HeroesRepository()) : ViewModel() {
    private val heroesList: List<Hero> = repository.getHeroesList()

    internal fun getHeroesList() = heroesList
}
