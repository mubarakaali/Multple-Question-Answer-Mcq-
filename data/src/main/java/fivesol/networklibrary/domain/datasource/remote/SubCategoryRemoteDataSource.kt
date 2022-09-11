package fivesol.networklibrary.domain.datasource.remote

import kotlinx.coroutines.flow.Flow
import fivesol.networklibrary.domain.models.Resource
import fivesol.networklibrary.domain.models.sub_category.SubCategoryResponse

interface SubCategoryRemoteDataSource {
     fun getSubCategories(path:String): Flow<Resource<SubCategoryResponse>>
}