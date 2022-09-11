package fivesol.networklibrary.domain.repository.subcategory

import kotlinx.coroutines.flow.StateFlow
import fivesol.networklibrary.domain.datasource.local.SubCategoryMcqPersistenceDataSource
import fivesol.networklibrary.domain.datasource.remote.SubCategoryRemoteDataSource
import fivesol.networklibrary.domain.models.Resource
import fivesol.networklibrary.domain.models.sub_category.Mcq
import fivesol.networklibrary.domain.repository.utils.CachedFirstStrategy
import javax.inject.Inject

class SubCategoryRepositoryImp @Inject constructor(
    private val remoteDataSource: SubCategoryRemoteDataSource,
    private val localDataSource: SubCategoryMcqPersistenceDataSource
) : SubCategoryRepository {
    override suspend fun getSubCategoriesMcq(
        path: String,
        categorId:Int,
        dataStrategy: CachedFirstStrategy
    ): StateFlow<Resource<List<Mcq>>> {
        return dataStrategy.performGetOperation(
            getFromCache = {
                localDataSource.getSubMcqCategoryList(categorId = categorId)
            },
            getFromRemote = {
                remoteDataSource.getSubCategories(path)
            },
            updateCache = {
                localDataSource.insertSubMcqCategoryList(it.mcqs!!)
            }
        )
    }

}