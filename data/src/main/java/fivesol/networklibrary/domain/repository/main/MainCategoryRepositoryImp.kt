package fivesol.networklibrary.domain.repository.main

import kotlinx.coroutines.flow.StateFlow
import fivesol.networklibrary.domain.repository.utils.CachedFirstStrategy
import fivesol.networklibrary.domain.datasource.local.MainCategoryPersistenceDataSource
import fivesol.networklibrary.domain.datasource.remote.MainCategoryRemoteDataSource
import fivesol.networklibrary.domain.models.Resource
import fivesol.networklibrary.domain.models.main_category.Data
import javax.inject.Inject

class MainCategoryRepositoryImp @Inject constructor(
    private val remoteDataSource: MainCategoryRemoteDataSource,
    private val localDataSource: MainCategoryPersistenceDataSource
) : MainCategoryRepository {

    override suspend fun getCategoryList(dataStrategy: CachedFirstStrategy): StateFlow<Resource<List<Data>>> =
       dataStrategy.performGetOperation(
           getFromCache = {
               localDataSource.getMainCategoryList()
           },
           getFromRemote = {
               remoteDataSource.getCategoryList()
           },
           updateCache = {
               localDataSource.insertMainCategoryList(it.data!!)
           }
       )
}