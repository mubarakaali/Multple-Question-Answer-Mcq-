package fivesol.networklibrary.data.remote.datasource

import kotlinx.coroutines.flow.Flow
import fivesol.networklibrary.data.remote.api.ApiService
import fivesol.networklibrary.domain.datasource.remote.SubCategoryRemoteDataSource
import fivesol.networklibrary.domain.models.Resource
import fivesol.networklibrary.domain.models.sub_category.SubCategoryResponse
import javax.inject.Inject

class SubCategoryRemoteDataSourceImp @Inject constructor(private val apiService: ApiService) :
    SubCategoryRemoteDataSource, BaseRemoteDataSource() {

        override fun getSubCategories(path: String): Flow<Resource<SubCategoryResponse>>  = safeApiCall {
            apiService.getSubCategories(path)
        }
}