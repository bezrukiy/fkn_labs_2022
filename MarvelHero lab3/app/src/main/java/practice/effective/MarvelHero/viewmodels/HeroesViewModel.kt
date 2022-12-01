package practice.effective.MarvelHero.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import practice.effective.MarvelHero.models.Hero
import practice.effective.MarvelHero.navigation.ErrorMessage
import practice.effective.MarvelHero.network.ResponseWrapper
import practice.effective.MarvelHero.network.dtos.HeroDto
import practice.effective.MarvelHero.repositories.HeroesRepository
import practice.effective.MarvelHero.ui.HeroUiState

class HeroesViewModel(private val repository: HeroesRepository = HeroesRepository()) : ViewModel() {
    private val _state = MutableStateFlow<HeroUiState>(HeroUiState.Empty)
    val state = _state.asStateFlow()

    internal fun getHeroesList(navController: NavController) {
        viewModelScope.launch {
            when (val res = repository.getHeroesList().single()) {
                is ResponseWrapper.NetworkError -> navController.navigate(ErrorMessage.route)
                is ResponseWrapper.GenericError -> navController.navigate(ErrorMessage.route)
                is ResponseWrapper.Success -> {
                    val list: MutableList<Hero> = mutableStateListOf()
                    res.value.forEach { elem -> list.add(mapDtoToEntity(elem)) }
                    _state.value = HeroUiState.HeroesData(list)
                }
            }
        }
    }

    internal fun getHero(id: String, navController: NavController) {
        viewModelScope.launch {
            when (val res = repository.getHero(id).single()) {
                is ResponseWrapper.NetworkError -> navController.navigate(ErrorMessage.route)
                is ResponseWrapper.GenericError -> navController.navigate(ErrorMessage.route)
                is ResponseWrapper.Success -> {
                    val list: MutableList<Hero> = mutableStateListOf()
                    list.add(mapDtoToEntity(res.value))
                    _state.value = HeroUiState.HeroesData(list)
                }
            }
        }
    }

    private fun mapDtoToEntity(dto: HeroDto): Hero {
        return (
                Hero(
                    id = dto.id.toString(),
                    name = dto.name,
                    description = if (dto.description != "") dto.description else "Hi! I'm ${dto.name}",
                    imageUrl = dto.thumbnail.path.replace("http:", "https:") +
                            "/portrait_uncanny." +
                            dto.thumbnail.extension
                )
                )
    }
}
