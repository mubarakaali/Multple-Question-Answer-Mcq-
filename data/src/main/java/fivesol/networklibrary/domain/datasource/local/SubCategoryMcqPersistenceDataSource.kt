package fivesol.networklibrary.domain.datasource.local

import kotlinx.coroutines.flow.StateFlow
import fivesol.networklibrary.domain.models.sub_category.Mcq

interface SubCategoryMcqPersistenceDataSource {
    suspend fun getSubMcqCategoryList(categorId:Int): StateFlow<List<Mcq>>
    suspend fun insertSubMcqCategoryList(McqData: List<Mcq>)
}