package fivesol.networklibrary.domain.repository.main

import kotlinx.coroutines.flow.StateFlow
import fivesol.networklibrary.domain.repository.utils.CachedFirstStrategy
import fivesol.networklibrary.domain.models.Resource
import fivesol.networklibrary.domain.models.main_category.Data

interface MainCategoryRepository {
     suspend fun getCategoryList(dataStrategy: CachedFirstStrategy): StateFlow<Resource<List<Data>>>
}