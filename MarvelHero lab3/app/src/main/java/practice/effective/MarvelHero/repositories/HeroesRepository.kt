package practice.effective.MarvelHero.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import practice.effective.MarvelHero.network.MarvelApi
import practice.effective.MarvelHero.network.ResponseWrapper
import practice.effective.MarvelHero.network.dtos.HeroDto
import practice.effective.MarvelHero.network.safeApiCall

class HeroesRepository(private val api: MarvelApi = MarvelApi) {
    internal fun getHeroesList(): Flow<ResponseWrapper<List<HeroDto>>> {
        val res: Flow<ResponseWrapper<List<HeroDto>>> = flow {
            val response =
                safeApiCall(Dispatchers.IO) { api.retrofitService.getAllHeroes().data.results }
            emit(response)
        }
        return res
    }

    internal fun getHero(id: String): Flow<ResponseWrapper<HeroDto>> {
        val res: Flow<ResponseWrapper<HeroDto>> = flow {
            val response =
                safeApiCall(Dispatchers.IO) { api.retrofitService.getHeroById(id).data.results.single() }
            emit(response)
        }
        return res
    }
}
