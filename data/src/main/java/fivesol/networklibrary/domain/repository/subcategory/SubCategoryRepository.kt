package fivesol.networklibrary.domain.repository.subcategory

import fivesol.networklibrary.domain.models.Resource
import fivesol.networklibrary.domain.models.sub_category.SubCategoryResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import fivesol.networklibrary.domain.models.sub_category.Mcq
import fivesol.networklibrary.domain.repository.utils.CachedFirstStrategy

interface SubCategoryRepository {
    suspend fun getSubCategoriesMcq(path: String,categorId:Int,dataStrategy: CachedFirstStrategy):StateFlow<Resource<List<Mcq>>>
}