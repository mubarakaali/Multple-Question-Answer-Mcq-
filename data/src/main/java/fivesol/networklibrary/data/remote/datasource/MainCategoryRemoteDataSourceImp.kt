package fivesol.networklibrary.data.remote.datasource

import kotlinx.coroutines.flow.Flow
import fivesol.networklibrary.domain.models.main_category.MainCategoryResponse
import fivesol.networklibrary.data.remote.api.ApiService
import fivesol.networklibrary.domain.datasource.remote.MainCategoryRemoteDataSource
import fivesol.networklibrary.domain.models.Resource
import javax.inject.Inject

class MainCategoryRemoteDataSourceImp @Inject constructor(private val apiService: ApiService) :
    BaseRemoteDataSource(), MainCategoryRemoteDataSource {

    override fun getCategoryList(): Flow<Resource<MainCategoryResponse>> = safeApiCall {
        apiService.getPostList()
        }
    }