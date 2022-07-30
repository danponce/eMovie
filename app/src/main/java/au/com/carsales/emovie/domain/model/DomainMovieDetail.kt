package au.com.carsales.emovie.domain.model

data class DomainMovieDetail(
    var id: Int,
    val posterPath: String,
    val backdropPath: String,
    val originalTitle: String,
    val releaseDate: String,
    val runtime: Int,
    val overview: String,
    val genres: List<String>,
    val videos: List<DomainMovieVideoItem>
)