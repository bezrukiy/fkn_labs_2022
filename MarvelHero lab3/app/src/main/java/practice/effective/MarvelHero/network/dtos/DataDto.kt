package practice.effective.MarvelHero.network.dtos

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DataDto(
    @Json(name = "results")
    val results: List<HeroDto>,
)
