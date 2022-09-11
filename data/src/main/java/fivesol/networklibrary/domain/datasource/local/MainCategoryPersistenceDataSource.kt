package fivesol.networklibrary.domain.datasource.local

import kotlinx.coroutines.flow.StateFlow
import fivesol.networklibrary.domain.models.main_category.Data

interface MainCategoryPersistenceDataSource {
    suspend fun getMainCategoryList(): StateFlow<List<Data>>
    suspend fun insertMainCategoryList(mainData: List<Data>)

}