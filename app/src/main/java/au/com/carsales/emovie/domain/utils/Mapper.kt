package au.com.carsales.emovie.domain.utils

/**
 * Interface for model mappers. It provides helper methods that facilitate
 * retrieving of models from outer layers
 *
 * @param <Source> the providing model input type
 * @param <Destination> the external model output type
 */
interface Mapper<in Source, out Destination> {

    fun executeMapping(type: Source?): Destination?

}