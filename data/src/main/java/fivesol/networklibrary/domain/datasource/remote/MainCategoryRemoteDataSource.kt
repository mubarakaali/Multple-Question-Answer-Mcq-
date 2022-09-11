package fivesol.networklibrary.domain.datasource.remote

import kotlinx.coroutines.flow.Flow
import fivesol.networklibrary.domain.models.Resource
import fivesol.networklibrary.domain.models.main_category.MainCategoryResponse

interface MainCategoryRemoteDataSource {
    fun getCategoryList():Flow<Resource<MainCategoryResponse>>
}