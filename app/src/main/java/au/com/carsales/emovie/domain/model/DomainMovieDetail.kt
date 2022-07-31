package au.com.carsales.emovie.domain.model

data class DomainMovieDetail(
    var id: Int,
    val posterPath: String,
    val backdropPath: String,
    val originalTitle: String,
    val title: String,
    val releaseDate: String,
    val runtime: Int,
    val overview: String,
    val genres: List<String>,
    val voteAverage : Double,
    val voteCount : Int,
    val videos: List<DomainMovieVideoItem>
)