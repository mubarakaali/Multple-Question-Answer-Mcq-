package fivesol.networklibrary.domain.repository.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import fivesol.networklibrary.domain.models.Resource

interface CachedDataAccessStrategy {
    suspend fun <PD,RD> performGetOperation(
        getFromCache:(suspend ()->StateFlow<PD>),
        getFromRemote:(()->Flow<Resource<RD>>),
        updateCache:(suspend (RD)->Unit)
    ):StateFlow<Resource<PD>>
}