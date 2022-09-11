package fivesol.networklibrary.domain.repository.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

import fivesol.networklibrary.domain.models.Resource
import kotlin.coroutines.coroutineContext

/**
 * This implementation of [CachedDataAccessStrategy] emits an empty [Resource.Loading] first
 *
 * then it emits a [Resource.Loading] with cached data received from the passed parameter
 *
 * then it fetches data from remote, if successful that data is stored in cache, if unsuccessful,
 * it emits a [Resource.Invalid] object
 *
 * At the end, the state flow emits all from the cache
 */
object CachedFirstStrategy : CachedDataAccessStrategy {
    override suspend fun <PD,RD> performGetOperation(
        getFromCache: suspend () -> StateFlow<PD>,
        getFromRemote: () -> Flow<Resource<RD>>,
        updateCache: suspend (RD) -> Unit
    ): StateFlow<Resource<PD>> = flow {
        getFromCache().take(1).collect {
            emit(Resource.Loading(it))
        }
        getFromRemote().collect { remoteResponse ->
            if (remoteResponse !is Resource.Loading) {
                if (remoteResponse is Resource.Valid)
                    updateCache(remoteResponse.data)
                else if (remoteResponse is Resource.Invalid) {
                    emit(Resource.Invalid(remoteResponse.message))
                    delay(500)
                }
                emitAll(getFromCache().map {
                    Resource.Valid(it)
                })
            }
        }
    }.stateIn(
        CoroutineScope(coroutineContext),
        SharingStarted.Eagerly,
        Resource.Loading()
    )
}